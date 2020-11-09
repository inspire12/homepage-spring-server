package com.inspire12.homepage.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SignupRequest {

    @JsonProperty("username")
    @NotNull
    @Min(3)
    String username;
    @NotNull
    String password;
    String repassword;

    @JsonProperty("student_id")
    String studentId;
    @NotNull
    @JsonProperty("realname")
    String realName;
    @NotNull
    @Email
    String email;

    @NotNull
    @JsonProperty("email-token")
    String emailToken;
    public static SignupRequest create() {
        SignupRequest signup = new SignupRequest();
        signup.setUsername("서영학");

        return signup;
    }
}
