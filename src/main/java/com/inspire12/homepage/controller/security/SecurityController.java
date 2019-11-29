package com.inspire12.homepage.controller.security;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.homepage.model.entity.AuthenticationToken;
import com.inspire12.homepage.model.entity.User;
import com.inspire12.homepage.security.AuthProvider;
import com.inspire12.homepage.security.UserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
public class SecurityController {
    @Autowired
    AuthProvider authProvider;

    @Autowired
    UserDetailService userDetailService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String registerUser(@RequestParam Map<String, String> requestBody, Model model) throws InvalidKeyException, NoSuchAlgorithmException {
        String name = requestBody.get("username");
        String password = requestBody.get("password");
        String email = requestBody.get("email");
        String encryptedPassword = authProvider.encrypt(name, password);
        User user = User.create(name, email, encryptedPassword);
        try {
            userDetailService.saveUser(user);
            model.addAttribute("status","signup");
            model.addAttribute("name","index");
            return "index";
        }catch (Exception e){
            model.addAttribute("status","fail");
        }
        model.addAttribute("name","signup");
        return "signup";
    }


    @RequestMapping(value="/login", method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public AuthenticationToken login(
            @RequestParam Map<String, String> authenticationRequest, Model model,
            HttpSession session
    ) {
        String username = authenticationRequest.get("username");
        String password = authenticationRequest.get("password");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authProvider.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        User user = userDetailService.readUser(username);
        return new AuthenticationToken(user.getName(), user.getAuthorities(), session.getId());
    }
}
