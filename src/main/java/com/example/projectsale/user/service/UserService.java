package com.example.projectsale.user.service;

import com.example.projectsale.user.dto.request.LoginRequest;
import com.example.projectsale.user.dto.request.UserCreateRequest;
import com.example.projectsale.user.dto.request.UserSearchDtoRequest;
import com.example.projectsale.user.dto.request.UserUpdateDtoRequest;
import com.example.projectsale.utils.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface UserService {

    ResponseEntity<Response> createUser(UserCreateRequest request);

    ResponseEntity<Response> login(LoginRequest request);

    ResponseEntity<Response> confirmUser(String confirmationCode);

    ResponseEntity<Response> updateUser(String jwt, UserUpdateDtoRequest request);

    ResponseEntity<Response> findUsersByCondition(UserSearchDtoRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    ResponseEntity<Response> getUserProfile(String jwt);

    ResponseEntity<Response> getPermissions(String jwt);

}
