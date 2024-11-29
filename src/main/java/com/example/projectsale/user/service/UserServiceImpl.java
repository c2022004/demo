package com.example.projectsale.user.service;

import com.example.projectsale.confirmtoken.ConfirmationToken;
import com.example.projectsale.confirmtoken.ConfirmationTokenDTO;
import com.example.projectsale.confirmtoken.ConfirmationTokenService;
import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.email.EmailService;
import com.example.projectsale.enums.SystemEnumStatus;
import com.example.projectsale.exception.ApiRequestException;
import com.example.projectsale.image.ImageService;
import com.example.projectsale.jwt.JwtService;
import com.example.projectsale.permissionrole.entity.PermissionRole;
import com.example.projectsale.token.Token;
import com.example.projectsale.token.TokenRepo;
import com.example.projectsale.token.TokenType;
import com.example.projectsale.user.constant.UserConstant;
import com.example.projectsale.user.dto.UserDto;
import com.example.projectsale.user.dto.request.LoginRequest;
import com.example.projectsale.user.dto.request.UserCreateRequest;
import com.example.projectsale.user.dto.request.UserSearchDtoRequest;
import com.example.projectsale.user.dto.request.UserUpdateDtoRequest;
import com.example.projectsale.user.dto.response.RefreshTokenDtoResponse;
import com.example.projectsale.user.dto.response.UserLoginDtoResponse;
import com.example.projectsale.user.dto.response.UserProfileDtoResponse;
import com.example.projectsale.user.entity.User;
import com.example.projectsale.user.mapper.UserDtoMapper;
import com.example.projectsale.user.repo.UserRepo;
import com.example.projectsale.userprofile.entity.UserProfile;
import com.example.projectsale.userprofile.repo.UserProfileRepo;
import com.example.projectsale.userrole.entity.UserRole;
import com.example.projectsale.utils.AbsServiceUtil;
import com.example.projectsale.utils.DateUtil;
import com.example.projectsale.utils.base.BaseUrlServiceUtil;
import com.example.projectsale.utils.response.Response;
import com.example.projectsale.utils.response.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl extends AbsServiceUtil implements UserService {

    private final UserRepo userRepo;

    private final EmailService emailService;

    private final ResponseUtil responseUtil;

    private final PasswordEncoder passwordEncoder;

    private final BaseUrlServiceUtil baseUrlServiceUtil;

    private final UserDtoMapper userDtoMapper;

    private final AuthenticationManager authenticationManager;

    private final ConfirmationTokenService confirmationTokenService;

    private final JwtService jwtService;

    private final TokenRepo tokenRepo;

    private final ImageService imageService;

    private final UserProfileRepo userProfileRepo;

    @Override
    public ResponseEntity<Response> createUser(UserCreateRequest request) {
        try {
            if (userRepo.existsUserByEmail(request.getEmail())) {
                return responseUtil.responseError("EM_001");
            }

            User user = userRepo.save(
                    User.builder()
                            .email(request.getEmail())
                            .password(passwordEncoder.encode(request.getPassword()))
                            .dateOfBirth(DateUtil.stringToDate(request.getDateOfBirth(), DateUtil.FORMAT_YYYY_MM_DD))
                            .status(SystemEnumStatus.NO_ACTIVE)
                            .isDeleted(SystemConstant.IS_DELETED_ACTIVE)
                            .build()
            );

            String token = String.valueOf(UUID.randomUUID());
            confirmationTokenService.saveConfirmationToken(
                    ConfirmationToken.builder()
                            .token(token)
                            .user(user)
                            .createdAt(LocalDateTime.now())
                            .expiresAt(LocalDateTime.now().plusMinutes(15))
                            .build()
            );

            String urlConfirm = baseUrlServiceUtil.getBaseUrl() + "/skeleton" +
                                SystemConstant.API_PUBLIC +
                                SystemConstant.VERSION_ONE + UserConstant.API_USER + "/confirm?token=" + token;
            emailService.send(request.getEmail(), emailService.buildMailConfirm(urlConfirm), "Confirm account.");
            return responseUtil.responseSuccess("USR_001", userDtoMapper.apply(user));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiRequestException("CM_001");
        }
    }

    @Override
    public ResponseEntity<Response> login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = getUser(request.getEmail());

        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        Set<UserRole> role = user.getUserRoles();
        UserRole roleOne = role.iterator().next();

        UserLoginDtoResponse response = new UserLoginDtoResponse(jwtToken, jwtService.generateRefreshToken(user),
                TimeUnit.MICROSECONDS.toSeconds(jwtService.extractExpired(jwtToken).getTime()),roleOne.getRoleName());

        return responseUtil.responseSuccess("USR_002", response);
    }

    @Override
    public ResponseEntity<Response> confirmUser(String confirmationCode) {
        try {
            ConfirmationTokenDTO confirmationToken = confirmationTokenService.findByToken(confirmationCode);
            if (confirmationToken.getConfirmedAt() != null) {
                return responseUtil.responseError("CFT_002");
            }

            if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
                return responseUtil.responseError("CFT_003");
            }

            confirmationTokenService.setConfirmAt(confirmationCode);
            userRepo.activeUserByEmail(confirmationToken.getEmail());
            return responseUtil.responseSuccess("CFT_004", confirmationCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiRequestException("CM_001");
        }
    }

    @Override
    public ResponseEntity<Response> updateUser(String jwt, UserUpdateDtoRequest request) {
        User user = getUserByToken(jwt);
        UserProfile userProfile = Objects.requireNonNullElseGet(user.getUserProfile(), UserProfile::new);

        if (Objects.nonNull(request.getAvatar()) && !request.getAvatar().isEmpty()) {
            String avatar = imageService.saveImage(request.getAvatar());
            user.setAvatar(avatar);
        }

        if (StringUtils.isNotBlank(request.getDob())) {
            Date dob = DateUtil.stringToDate(request.getDob());
            if (!dob.equals(user.getDateOfBirth())) {
                user.setDateOfBirth(dob);
            }
        }

        updateUserProfileField(userProfile::getFirstName, userProfile::setFirstName, request.getFirstName());
        updateUserProfileField(userProfile::getLastName, userProfile::setLastName, request.getLastName());
        updateUserProfileField(userProfile::getPhoneNumber, userProfile::setPhoneNumber, request.getPhoneNumber());
        updateUserProfileField(userProfile::getAddress, userProfile::setAddress, request.getAddress());
        updateUserProfileField(userProfile::getCity, userProfile::setCity, request.getCity());
        updateUserProfileField(userProfile::getPostalCode, userProfile::setPostalCode, request.getPostTotalCode());
        updateUserProfileField(userProfile::getCountry, userProfile::setCountry, request.getCountry());

        userProfile.setUser(user);
        userProfileRepo.save(userProfile);
        userRepo.save(user);
        return responseUtil.responseSuccess("USR_P_001", null);
    }

    @Override
    public ResponseEntity<Response> findUsersByCondition(UserSearchDtoRequest request) {
        Pageable pageable = pageable(request.getPageNumber(), request.getPageSize());
        Specification<User> userSpec = UserSpec.findUsersByCondition(request.getKey(), request.getRole());
        Page<User> users = userRepo.findAll(userSpec, pageable);

        List<UserDto> userDtoList = users.stream()
                .map(userDtoMapper)
                .toList();

        return responseUtil.responsesSuccess("USR_003", userDtoList, pageable(pageable, users.getTotalPages()));
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith(SystemConstant.BEARER)) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepo.findUserByEmail(userEmail)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found!.."));
            if (jwtService.isValidToken(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = Response.builder()
                        .code("RFT_001")
                        .status(SystemConstant.STATUS_SUCCESS)
                        .data(new RefreshTokenDtoResponse(accessToken, refreshToken,
                                TimeUnit.MICROSECONDS.toSeconds(jwtService.extractExpired(accessToken).getTime())))
                        .message("Refresh token success!..")
                        .responseTime(responseTime())
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    @Override
    public ResponseEntity<Response> getUserProfile(String jwt) {
        User user = getUserByToken(jwt);
        UserProfileDtoResponse userProfile = userDtoMapper.applyUserProfile(user);
        return responseUtil.responseSuccess("USR_P_002", userProfile);
    }

    @Override
    public ResponseEntity<Response> getPermissions(String jwt) {
        User user = getUserByToken(jwt);
        Map<String, List<String>> rolePermissionsMap = user.getUserRoles().stream()
                .collect(groupingBy(
                        UserRole::getRoleName,
                        Collectors.flatMapping(userRole -> userRole.getRole().getPermissionRoles().stream()
                                .map(PermissionRole::getPermissionCode), Collectors.toList())
                ));

        return responseUtil.responseSuccess("USR_P_003", rolePermissionsMap);
    }

    private void updateUserProfileField(Supplier<String> getter, Consumer<String> setter, String newValue) {
        if (newValue != null && !newValue.isEmpty() && !newValue.trim().isEmpty()) {
            if (getter.get() == null || !newValue.equals(getter.get())) {
                setter.accept(newValue);
            }
        }
    }

    private User getUserByToken(String token) {
        final String jwt = token.substring(7);
        final String email = jwtService.extractUsername(jwt);
        return getUser(email);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepo.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .tokenType(TokenType.BEARER)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepo.save(token);
    }

    private User getUser(String email) {
        return userRepo.findUserByEmailAndStatus(email, SystemEnumStatus.ACTIVE)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
