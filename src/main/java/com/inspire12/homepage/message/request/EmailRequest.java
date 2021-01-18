package com.inspire12.homepage.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class EmailRequest {

    @JsonProperty("email")
    String email;

}
