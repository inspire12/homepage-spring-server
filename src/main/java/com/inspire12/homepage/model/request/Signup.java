package com.inspire12.homepage.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
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
}
