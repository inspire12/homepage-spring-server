package com.inspire12.homepage.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class EmailRequest {

    @JsonProperty("email")
    String email;
}
