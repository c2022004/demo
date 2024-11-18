package com.example.projectsale.confirmtoken;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ConfirmationTokenDTOMapper implements Function<ConfirmationToken, ConfirmationTokenDTO> {

    @Override
    public ConfirmationTokenDTO apply(ConfirmationToken confirmationToken) {
        return ConfirmationTokenDTO.builder()
                .createdAt(confirmationToken.getCreatedAt())
                .confirmedAt(confirmationToken.getConfirmedAt())
                .expiresAt(confirmationToken.getExpiresAt())
                .token(confirmationToken.getToken())
                .email(confirmationToken.getUser().getEmail())
                .build();
    }
}
