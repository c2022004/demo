package com.example.projectsale.user.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserCreateRequest {

    private String email;

    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY", timezone = "GMT+7")
    private String dateOfBirth;

    private MultipartFile avatar;

}
