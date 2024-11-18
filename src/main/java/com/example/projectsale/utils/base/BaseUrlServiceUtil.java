package com.example.projectsale.utils.base;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseUrlServiceUtil {

    private final HttpServletRequest request;

    public String getBaseUrl() {
        return request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getRequestURI()));
    }

}
