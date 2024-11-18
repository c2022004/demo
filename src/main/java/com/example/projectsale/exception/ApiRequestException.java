package com.example.projectsale.exception;

import com.example.projectsale.utils.AbsServiceUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiRequestException extends RuntimeException {

    private AbsServiceUtil absServiceUtil;

    private final String code;
    private final String message;

    public ApiRequestException(String code) {
        this.code = code;
        this.message = absServiceUtil.getMessage(code);
    }

}
