package com.inspire12.homepage.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Value
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    @JsonProperty("email")
    String email;

}
