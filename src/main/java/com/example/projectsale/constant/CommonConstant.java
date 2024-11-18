package com.example.projectsale.constant;

import jakarta.servlet.http.HttpServletRequest;

public class CommonConstant {

    public static final String[] URI_BY_BASS = {
            SystemConstant.API_PUBLIC + "/**",
            "/api-docs/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-ui.html/**",
            "/swagger-ui/**"
    };

    public static boolean byBass(HttpServletRequest req) {
        String reqUri = req.getRequestURI();
        return reqUri.contains("/swagger-ui.html")
               || reqUri.contains("/v2/api-docs");
    }
}
