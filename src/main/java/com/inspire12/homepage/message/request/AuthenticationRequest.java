package com.inspire12.homepage.message.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Value
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    String username;
    String password;
}
