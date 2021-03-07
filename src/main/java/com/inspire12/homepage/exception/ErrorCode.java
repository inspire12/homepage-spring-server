package com.inspire12.homepage.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "Invalid Input Value", HttpStatus.INTERNAL_SERVER_ERROR),
    METHOD_NOT_ALLOWED(405, "Invalid Input Value"),
    HANDLE_ACCESS_DENIED(403, "Access is Denied"),
    DATA_NOT_FOUND(404, "Data not Found"),

    // Login
    NOT_AUTHORIZE(401, "NOT AUTH", HttpStatus.UNAUTHORIZED),

    // Signup Login
    EMAIL_DUPLICATION(200, "Email is Duplication"),
    LOGIN_INPUT_INVALID(201, "Login input is invalid"),

    INTERNAL_SERVER_ERROR(500, "Internal error"),
    ;

    private Integer code;
    private String message;
    private HttpStatus httpStatus;


    ErrorCode(int code, String message) {
        this.message = message;
        this.code = code;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}

