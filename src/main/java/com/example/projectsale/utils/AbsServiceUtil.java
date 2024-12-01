package com.example.projectsale.utils;

import com.example.projectsale.user.entity.User;
import com.example.projectsale.utils.response.ResponseUtil;
import com.example.projectsale.utils.response.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public abstract class AbsServiceUtil {

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }

        return null;
    }

    public Long responseTime() {
        return TimeUnit.MICROSECONDS.toSeconds(System.currentTimeMillis());
    }

    public Pageable pageable(int page, int size) {
        page = page < 1 ? 1 : page;
        size = size <= 0 ? 9999 : size;

        Pageable pageable = PageRequest.of(page - 1, size);
        return pageable;
    }

    public Responses.PageableResponse pageable(Pageable pageable, int totalPage) {
        return new Responses.PageableResponse(totalPage, pageable);
    }

}
