package com.inspire12.homepage.exception;

public class NotAuthorizeException extends CommonException {

    private static final long serialVersionUID = -5287215516606455104L;
    public NotAuthorizeException() {
        super(ErrorCode.NOT_AUTHORIZE);
    }
}
