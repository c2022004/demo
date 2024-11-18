package com.example.projectsale.user.mapper;

import com.example.projectsale.user.dto.UserDto;
import com.example.projectsale.user.dto.response.UserProfileDtoResponse;
import com.example.projectsale.user.entity.User;
import com.example.projectsale.userprofile.entity.UserProfile;
import com.example.projectsale.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserDtoMapper implements Function<User, UserDto> {

    private final ModelMapper modelMapper;

    @Override
    public UserDto apply(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public UserProfileDtoResponse applyUserProfile(User user) {
        UserProfile userProfile = user.getUserProfile();
        return UserProfileDtoResponse.builder()
                .email(user.getEmail())
                .avatar(Objects.nonNull(user.getAvatar()) ? user.getAvatar() : null)
                .dob(Objects.nonNull(user.getDateOfBirth()) ?
                        DateUtil.dateToString(user.getDateOfBirth(), DateUtil.FORMAT_DD_MM_YYYY) : null)
                .firstName(Objects.nonNull(userProfile) ? userProfile.getFirstName() : null)
                .lastName(Objects.nonNull(userProfile) ? userProfile.getLastName() : null)
                .phoneNumber(Objects.nonNull(userProfile) ? userProfile.getPhoneNumber() : null)
                .postalCode(Objects.nonNull(userProfile) ? userProfile.getPostalCode() : null)
                .city(Objects.nonNull(userProfile) ? userProfile.getCity() : null)
                .country(Objects.nonNull(userProfile) ? userProfile.getCountry() : null)
                .address(Objects.nonNull(userProfile) ? userProfile.getAddress() : null)
                .build();
    }
}
