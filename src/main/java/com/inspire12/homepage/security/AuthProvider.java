package com.inspire12.homepage.security;


import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

//    @Autowired
//    BCryptPasswordEncoder encoder;

    private final Environment env;

    private final UserRepository repository;

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

            Optional<AppUser> user = repository.findByUsernameAndPassword(name, password);
            repository.updateUserLastLoginTime(name);

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.get().getRole().get(0))); // TODO

            Authentication auth = new UsernamePasswordAuthenticationToken(user.get().getUsername(), user.get().getPassword(), grantedAuthorities);
            return auth;

        } catch (Exception e) {
            log.error("{} : {}", e.getClass().getSimpleName(), e.getMessage());
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
