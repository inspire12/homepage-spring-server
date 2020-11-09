package com.inspire12.homepage.security;


import com.inspire12.homepage.model.entity.User;
import com.inspire12.homepage.repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AuthProvider implements AuthenticationProvider {

    private Logger logger = LoggerFactory.getLogger(AuthProvider.class);

//    @Autowired
//    BCryptPasswordEncoder encoder;

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository repository;

    public String encrypt(String key, String salt) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        return Base64.encodeBase64String(sha256_HMAC.doFinal(salt.getBytes()));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String name = authentication.getName();
            String password = encrypt(name, authentication.getCredentials().toString());

            Optional<User> user = Optional.of(repository.findByUsernameAndPassword(name, password));
            repository.updateUserLastLoginTime(name);

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.get().getRole()));

            Authentication auth = new UsernamePasswordAuthenticationToken(user.get().getUsername(), user.get().getPassword(), grantedAuthorities);
            return auth;

        } catch (Exception e) {
            logger.error(e.getClass().getSimpleName() + " : " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
