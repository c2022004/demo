package com.example.projectsale.exception;

public record ApiException(
        String code,
        Integer status,
        String message,
        long responseTime
) {
}
