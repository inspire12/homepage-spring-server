package com.inspire12.homepage.message.request;

import lombok.Value;

@Value
public class AuthenticationRequest {
    String username;
    String password;
}
