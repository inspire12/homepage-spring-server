package com.inspire12.homepage.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseMessage {
    Integer code;
    String message;
    Object result;
}
