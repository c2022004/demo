package com.example.projectsale.user.dto.response;

public record UserLoginDtoResponse(
        String accessToken,
        String refreshToken,
        Long expiration,
        String role
) {
}
