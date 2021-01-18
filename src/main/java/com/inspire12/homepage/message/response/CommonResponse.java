package com.inspire12.homepage.message.response;

import lombok.Value;

@Value
public class CommonResponse<T> {
    Integer code;
    T result;
}
