package com.inspire12.homepage.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Signup {

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
    String email;

    public static Signup create() {
        Signup signup = new Signup();
        signup.setUsername("서영학");

        return signup;
    }
}
