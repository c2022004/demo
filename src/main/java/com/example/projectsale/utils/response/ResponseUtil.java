package com.example.projectsale.utils.response;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.utils.AbsServiceUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil extends AbsServiceUtil {

    public ResponseEntity<Response> responseSuccess(String code, Object data, HttpStatus status) {
        return new ResponseEntity<>(response(code, SystemConstant.STATUS_SUCCESS, data, getMessage(code), responseTime()), status);
    }

    public ResponseEntity<Response> responseSuccess(String code, Object data) {
        return new ResponseEntity<>(response(code, SystemConstant.STATUS_SUCCESS, data, getMessage(code), responseTime()), HttpStatus.OK);
    }

    public ResponseEntity<Response> responsesSuccess(String code, Object data, Responses.PageableResponse pageableResponse, HttpStatus status) {
        return new ResponseEntity<>(responses(code, SystemConstant.STATUS_SUCCESS, data, getMessage(code), responseTime(), pageableResponse), status);
    }

    public ResponseEntity<Response> responsesSuccess(String code, Object data, Responses.PageableResponse pageableResponse) {
        return new ResponseEntity<>(responses(code, SystemConstant.STATUS_SUCCESS, data, getMessage(code), responseTime(), pageableResponse), HttpStatus.OK);
    }

    public ResponseEntity<Response> responseError(String code) {
        return new ResponseEntity<>(response(code, SystemConstant.STATUS_BAD_REQUEST, null, getMessage(code), responseTime()), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Response> responseError(String code, HttpStatus status) {
        return new ResponseEntity<>(response(code, SystemConstant.STATUS_BAD_REQUEST, null, getMessage(code), responseTime()), status);
    }

    public ResponseEntity<Response> responseError(String code, Object data, HttpStatus status) {
        return new ResponseEntity<>(response(code, SystemConstant.STATUS_BAD_REQUEST, data, getMessage(code), responseTime()), status);
    }

    private Response response(String code, Integer status, Object data, String message, Long responseTime) {
        return Response.builder()
                .code(code)
                .status(status)
                .data(data)
                .message(message)
                .responseTime(responseTime)
                .build();
    }

    private Responses responses(String code, Integer status,
                                Object data, String message,
                                Long responseTime, Responses.PageableResponse pageableResponse
    ) {
        return Responses.builder()
                .code(code)
                .status(status)
                .data(data)
                .message(message)
                .responseTime(responseTime)
                .meta(pageableResponse)
                .build();
    }

}
