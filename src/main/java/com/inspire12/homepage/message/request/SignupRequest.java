package com.inspire12.homepage.message.request;

import lombok.Value;

@Value
public class SignupRequest {

    String username;
    String password;
    String repassword;
    String studentId;
    String realname;
    String email;

    String emailToken;
}
