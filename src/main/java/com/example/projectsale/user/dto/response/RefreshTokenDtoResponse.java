package com.example.projectsale.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RefreshTokenDtoResponse {

    private String accessToken;

    private String refreshToken;

    private long extractExpired;

}
