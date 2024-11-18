package com.example.projectsale.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileDtoResponse {

    private String email;

    private String avatar;

    private String dob;

    private String firstName;

    private String lastName;

    private String fullName;

    private String phoneNumber;

    private String postalCode;

    private String city;

    private String country;

    private String address;

    public void setFullName() {
        this.fullName = this.firstName + " " + this.lastName;
    }

}
