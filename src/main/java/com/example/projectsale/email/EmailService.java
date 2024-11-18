package com.example.projectsale.email;

public interface EmailService {

    void send(String to, String email, String subject);

    String buildMailConfirm(String urlConfirm);

}
