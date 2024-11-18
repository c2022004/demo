package com.example.projectsale.utils.response;

import com.example.projectsale.utils.ApiCodeReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AbstractResponse {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private String responseId;
    private String resultCode = "00";
    private String resultMessage = "SUCCESS";
    private String responseTime;
    private Object data;

    public static AbstractResponse createSuccessResponse(Object data) {
        String responseId = UUID.randomUUID().toString();
        String time = generateExpireTime(DATE_TIME_FORMAT);
        return AbstractResponse.builder()
                .responseId(responseId)
                .resultCode("00")
                .resultMessage("SUCCESS")
                .responseTime(time)
                .data(data)
                .build();
    }

    public static AbstractResponse createErrorResponse(String errorCode, String errorMessage) {
        String responseId = UUID.randomUUID().toString();
        String time = generateExpireTime(DATE_TIME_FORMAT);
        return AbstractResponse.builder()
                .resultCode(errorCode)
                .responseId(responseId)
                .resultMessage(errorMessage)
                .responseTime(time)
                .build();
    }

    public static AbstractResponse exceptionResponse(Exception e) {
        String responseId = UUID.randomUUID().toString();
        String time = generateExpireTime(DATE_TIME_FORMAT);
        return AbstractResponse.builder()
                .resultCode(ApiCodeReference.OnBoarding.SYSTEM_ERROR_CODE)
                .responseId(responseId)
                .resultMessage(ApiCodeReference.OnBoarding.SYSTEM_ERROR_MSG)
                .responseTime(time)
                .build();
    }

    public static AbstractResponse createFailedResponse(String resultCode, String message, Object data) {
        String time = generateExpireTime(DATE_TIME_FORMAT);
        String responseId = UUID.randomUUID().toString();
        return AbstractResponse.builder()
                .resultCode(resultCode)
                .responseId(responseId)
                .resultMessage(message)
                .responseTime(time)
                .data(data)
                .build();
    }

    public static String generateExpireTime(String format) {
        SimpleDateFormat sdfDate = new SimpleDateFormat(format);
        return sdfDate.format(new Date());
    }
}
