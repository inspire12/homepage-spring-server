package com.inspire12.homepage.exception;

import com.inspire12.homepage.storage.StorageException;

public class NotAuthException extends Exception {
    public NotAuthException() {
        super("회원가입을 먼저 해주세요~");
    }

    public NotAuthException(String message) {
        super(message);
    }

    public NotAuthException(String message, Throwable cause) {
            super(message, cause);
    }
}
