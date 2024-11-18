package com.example.projectsale.user.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserCreateRequest {

    private String email;

    private String password;

    private String dateOfBirth;

    private MultipartFile avatar;

}
