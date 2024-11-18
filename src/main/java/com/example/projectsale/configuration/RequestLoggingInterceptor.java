package com.example.projectsale.configuration;

import com.example.projectsale.utils.CommonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Incoming request data: URI={}, Method={}, IP={}",
                request.getRequestURI(), request.getMethod(), CommonUtil.getClientIpAddress(request));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex != null) {
            log.error("Request failed: URI={}, Method={}, Error={}, IP={}",
                    request.getRequestURI(),
                    request.getMethod(),
                    ex.getMessage(),
                    CommonUtil.getClientIpAddress(request));
        }
    }
}
