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
public class ResponseMessage {
    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;

    private String view;


    public static ResponseMessage of(String message, int status, String code) {
        return new ResponseMessage(message, status, null, code, "auth");
    }

    public static ResponseMessage of(String message, HttpStatus httpStatus) {
        return new ResponseMessage(message, httpStatus.value(), null, httpStatus.getReasonPhrase(),"auth");
    }

    public static ResponseMessage of(ErrorCode errorCode) {
        return new ResponseMessage(errorCode.getMessage(), errorCode.getStatus(), null, errorCode.getCode(), "auth");
    }

    public static ResponseMessage of(ErrorCode errorCode, BindingResult bindingResult) {
        return new ResponseMessage(errorCode.getMessage(), errorCode.getStatus(), bindingResult.getFieldErrors(), errorCode.getCode(), "auth");
    }

}
