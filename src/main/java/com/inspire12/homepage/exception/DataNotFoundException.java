package com.inspire12.homepage.exception;

public class DataNotFoundException extends CommonException {
    public DataNotFoundException() {
        super(ErrorCode.DATA_NOT_FOUND);
    }
}
