package com.example.projectsale.user.controller.member;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.user.constant.UserConstant;
import com.example.projectsale.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "ofMember")
@RequiredArgsConstructor
@RequestMapping(SystemConstant.API_MEMBER + SystemConstant.VERSION_ONE + UserConstant.API_USER)
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String jwt) {
        return userService.getUserProfile(jwt);
    }

    @GetMapping("/permission")
    public ResponseEntity<?> getPermissions(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String jwt) {
        return userService.getPermissions(jwt);
    }

}
