package com.example.projectsale.user.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserUpdateDtoRequest {

    private MultipartFile avatar;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dob;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address;

    private String city;

    private String postTotalCode;

    private String country;

}
