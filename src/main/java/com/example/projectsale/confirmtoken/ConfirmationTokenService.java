package com.example.projectsale.confirmtoken;

public interface ConfirmationTokenService {

    ConfirmationTokenDTO findByToken(String token);

    void setConfirmAt(String token);

    void saveConfirmationToken(ConfirmationToken token);
}
