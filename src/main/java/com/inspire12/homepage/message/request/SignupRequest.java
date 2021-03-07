package com.inspire12.homepage.message.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Value
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    String username;
    String password;
    String repassword;
    String studentId;
    String realname;
    String email;

    String emailToken;
}
