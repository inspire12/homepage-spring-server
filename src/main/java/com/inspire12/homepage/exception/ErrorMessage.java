package com.inspire12.homepage.exception;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

//https://cheese10yun.github.io/spring-guide-exception/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ErrorMessage {
    private String message;
    private int status;
    private List<FieldError> errors;
    private Integer code;

    private String view;


    public static ErrorMessage of(String message, int status, Integer code) {
        return new ErrorMessage(message, status, null, code, "auth");
    }

    public static ErrorMessage of(String message, HttpStatus httpStatus) {
        return new ErrorMessage(message, httpStatus.value(), null, httpStatus.value(),"auth");
    }

    public static ErrorMessage of(ErrorCode errorCode) {
        return new ErrorMessage(errorCode.getMessage(), errorCode.getHttpStatus().value(), null, errorCode.getCode(), "auth");
    }

    public static ErrorMessage of(ErrorCode errorCode, BindingResult bindingResult) {
        return new ErrorMessage(errorCode.getMessage(), errorCode.getHttpStatus().value(), bindingResult.getFieldErrors(), errorCode.getCode(), "auth");
    }

}
