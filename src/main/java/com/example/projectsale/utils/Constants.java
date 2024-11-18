package com.example.projectsale.utils;

public class Constants {

    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";
    public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String REQUEST_ID_KEY = "RequestId";

    public final class KEYCLOAK_ERROR_CODE {
        public static final String TOKEN_EXPIRED = "KC001";

        public static final String TOKEN_EXPIRED_MESSAGE = "Token verify failed or expired";

        public static final String USER_NOT_FOUND = "KC002";

        public static final String USER_NOT_FOUND_MESSAGE = "User not found or not match";
    }

}
