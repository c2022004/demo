package com.example.projectsale.user.controller.admin;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.user.constant.UserConstant;
import com.example.projectsale.user.dto.request.UserSearchDtoRequest;
import com.example.projectsale.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "ofAdmin")
@RequiredArgsConstructor
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_ONE + UserConstant.API_USER)
public class UserController {

    private final UserService userService;

    @PostMapping("/get")
    public ResponseEntity<?> findUsersByCondition(@RequestBody UserSearchDtoRequest request) {
        return userService.findUsersByCondition(request);
    }

}
