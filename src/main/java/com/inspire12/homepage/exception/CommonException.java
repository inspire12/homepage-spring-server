package com.inspire12.homepage.exception;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

    private ErrorCode errorCode;

    public CommonException() { //TODO migration
    }

    public CommonException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public CommonException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CommonException(ErrorCode errorCode, Throwable throwable) {
        super(null, throwable);
        this.errorCode = errorCode;
    }
}
