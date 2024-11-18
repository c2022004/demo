package com.example.projectsale.user.controller.publicapi;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.user.constant.UserConstant;
import com.example.projectsale.user.dto.request.LoginRequest;
import com.example.projectsale.user.dto.request.UserCreateRequest;
import com.example.projectsale.user.dto.request.UserUpdateDtoRequest;
import com.example.projectsale.user.service.UserService;
import com.example.projectsale.utils.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController(value = "ofPublic")
@RequiredArgsConstructor
@RequestMapping(SystemConstant.API_PUBLIC + SystemConstant.VERSION_ONE + UserConstant.API_USER)
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }

    @GetMapping(UserConstant.API_CONFIRM_USER)
    public ResponseEntity<Response> confirmUser(@RequestParam("token") String confirmationCode) {
        return userService.confirmUser(confirmationCode);
    }

    @PostMapping(UserConstant.API_LOGIN)
    public ResponseEntity<Response> login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PutMapping(UserConstant.API_UPDATE_PROFILE)
    public ResponseEntity<Response> updateProfile(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String jwt,
            UserUpdateDtoRequest request) {
        return userService.updateUser(jwt, request);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        userService.refreshToken(request, response);
    }

}
