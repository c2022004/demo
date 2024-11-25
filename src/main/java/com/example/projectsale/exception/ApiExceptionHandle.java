package com.example.projectsale.exception;

import com.example.projectsale.constant.ResourceConstant;
import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.utils.AbsServiceUtil;
import com.example.projectsale.utils.response.AbstractResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandle extends AbsServiceUtil {

    private String extractErrorCode(String fullErrorMessage) {
        int startBracketIndex = fullErrorMessage.indexOf("Detail:");
        int endBracketIndex = fullErrorMessage.indexOf(']');

        if (startBracketIndex != -1 && endBracketIndex != -1 && startBracketIndex < endBracketIndex) {
            return fullErrorMessage.substring(startBracketIndex + 1, endBracketIndex).trim();
        }

        return fullErrorMessage;
    }

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<Object> handleException(ApiRequestException e) {
        ApiException exception = new ApiException(
                e.getCode(),
                SystemConstant.STATUS_BAD_REQUEST,
                e.getMessage(),
                responseTime()
        );
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException e) {
        List<String> listError = new ArrayList<>();

        if (e.getBindingResult().hasErrors()) {
            listError = e.getBindingResult().getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .map(this::getMessage)
                    .toList();
        }
        ApiException exception = new ApiException(
                ResourceConstant.CODE_ERROR_VALIDATION,
                SystemConstant.STATUS_BAD_REQUEST,
                listError.toString(),
                responseTime()
        );
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle exception when validate data
     *
     * @param e
     * @param request
     * @return errorResponse
     */
    @ExceptionHandler({
            ConstraintViolationException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class
    })
    @ResponseStatus(BAD_REQUEST)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "Handle exception when the data invalid. (@RequestBody, @RequestParam, @PathVariable)",
                                    summary = "Handle Bad Request",
                                    value = """
                                            {
                                                 "timestamp": "2024-04-07T11:38:56.368+00:00",
                                                 "status": 400,
                                                 "path": "/api/v1/...",
                                                 "error": "Invalid Payload",
                                                 "message": "{data} must be not blank"
                                             }
                                            """
                            ))})
    })
    public ErrorResponse handleValidationException(Exception e, WebRequest request) {
        log.error(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("This wrong parameter!");
        errorResponse.setStatus(BAD_REQUEST.value());
        errorResponse.setResponseTime(responseTime());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));

        String message = e.getMessage();
        if (e instanceof MethodArgumentNotValidException) {
            int start = message.lastIndexOf("[") + 1;
            int end = message.lastIndexOf("]") - 1;
            message = message.substring(start, end);
            errorResponse.setError("Invalid Payload");
            errorResponse.setMessage(message);
        } else if (e instanceof MissingServletRequestParameterException) {
            errorResponse.setError("Invalid Parameter");
            errorResponse.setMessage(message);
        } else if (e instanceof ConstraintViolationException) {
            errorResponse.setError("Invalid Parameter");
            errorResponse.setMessage(message.substring(message.indexOf(" ") + 1));
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            errorResponse.setError("Invalid Parameter");
            errorResponse.setMessage(message.substring(message.indexOf(" ") + 1));
        } else {
            errorResponse.setError("Invalid Data");
            errorResponse.setMessage(message);
        }

        return errorResponse;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleException(DataIntegrityViolationException e) {
        log.error(e.getMessage());
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException exception = new ApiException(
                "SV_002",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                extractErrorCode(e.getMessage()),
                responseTime()
        );
        return new ResponseEntity<>(exception, internalServerError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleJwtException(Exception e, WebRequest request) {
        HttpServletRequest httpRequest = ((ServletRequestAttributes) request).getRequest();
        // Lấy thông tin method
        String method = httpRequest.getMethod();
        ErrorResponse errorResponse;
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        HttpStatus forbidden = HttpStatus.FORBIDDEN;
        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        String uri = request.getDescription(false).replace("uri=", "");

        if (e instanceof BadCredentialsException) {
            errorResponse = ErrorResponse.builder()
                    .code(unauthorized.getReasonPhrase())
                    .status(HttpStatus.valueOf(401).value())
                    .path(uri)
                    .message(e.getMessage())
                    .error("Authentication Failure")
                    .responseTime(responseTime())
                    .build();
            return new ResponseEntity<>(errorResponse, unauthorized);
        }

        if (e instanceof AccessDeniedException) {
            errorResponse = ErrorResponse.builder()
                    .code(forbidden.getReasonPhrase())
                    .status(HttpStatus.valueOf(403).value())
                    .path(uri)
                    .message(e.getMessage())
                    .error("Not Access!")
                    .responseTime(responseTime())
                    .build();
            return new ResponseEntity<>(errorResponse, forbidden);
        }

        if (e instanceof ExpiredJwtException) {
            errorResponse = ErrorResponse.builder()
                    .code(forbidden.getReasonPhrase())
                    .status(HttpStatus.valueOf(403).value())
                    .path(uri)
                    .message(e.getMessage())
                    .error("JWT Token already expired !")
                    .responseTime(responseTime())
                    .build();
            return new ResponseEntity<>(errorResponse, forbidden);
        }

        if (e instanceof SignatureException) {
            errorResponse = ErrorResponse.builder()
                    .code(forbidden.getReasonPhrase())
                    .status(HttpStatus.valueOf(403).value())
                    .path(uri)
                    .message(e.getMessage())
                    .error("JWT Signature not valid")
                    .responseTime(responseTime())
                    .build();
            return new ResponseEntity<>(errorResponse, forbidden);
        }

        ErrorServerResponse serverResponse = ErrorServerResponse.builder()
                .code("SV_001")
                .path(uri)
                .timestamp(AbstractResponse.generateExpireTime(AbstractResponse.DATE_TIME_FORMAT))
                .message(internalServerError.getReasonPhrase())
                .build();

        e.printStackTrace();
        log.error("Request failed: URI={}, Method={}, Error={}", uri, method, e.getMessage());
        return new ResponseEntity<>(serverResponse, internalServerError);
    }
}