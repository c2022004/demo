package com.example.projectsale.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Async("asyncTaskExecutor")
    @Override
    public void send(String to, String email, String subject) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("staying.booking.online@gmail.com", "Staying.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String buildMailConfirm(String urlConfirm) {
        return "<body style=\"font-family: Arial, sans-serif; background-color: #f7f7f7;\">" +
               "<div style=\"max-width: 600px; margin: 0 auto; padding: 20px; background-color: #ffffff; border-radius: 5px;\">" +
               "<h2 style=\"color: #333333; margin-bottom: 20px;\">Xin chào!</h2>" +
               "<p style=\"color: #555555; line-height: 1.5;\">Cảm ơn bạn đã đăng ký tài khoản. Để hoàn tất quá trình kích hoạt, vui lòng nhấp vào liên kết bên dưới:</p>" +
               "<p style=\"text-align: center; margin-top: 30px;\">" +
               "<a href=\"" + urlConfirm + "\" style=\"display: inline-block; padding: 10px 20px; background-color: #007bff; color: #ffffff; text-decoration: none; border-radius: 5px;\">Kích hoạt tài khoản</a>" +
               "</p>" +
               "<p style=\"color: #555555; line-height: 1.5; margin-top: 30px;\">Nếu liên kết trên không hoạt động, bạn có thể sao chép đường dẫn dưới đây và dán vào trình duyệt:</p>" +
               "<p style=\"color: #555555; line-height: 1.5;\">" + urlConfirm + "</p>" +
               "<p style=\"color: #555555; line-height: 1.5; margin-top: 30px;\">Xin cảm ơn!</p>" +
               "</div>" +
               "</body>";
    }

}
