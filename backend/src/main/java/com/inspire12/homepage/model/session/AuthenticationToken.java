package com.inspire12.homepage.model.session;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
public class AuthenticationToken {

    private String username;
    private Collection authorities;
    private String token;

    public AuthenticationToken(String username, Collection collection, String token) {
        this.username = username;
        this.authorities = collection;
        this.token = token;
    }
}