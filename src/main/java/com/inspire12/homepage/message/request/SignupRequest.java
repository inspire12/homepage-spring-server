package com.inspire12.homepage.message.request;

import lombok.Data;

@Data
public class SignupRequest {

    String username;
    String password;
    String repassword;
    String studentId;
    String realname;
    String email;

    String emailToken;

    public static SignupRequest create() {
        SignupRequest signup = new SignupRequest();
        signup.setUsername("서영학");
        return signup;
    }
}
