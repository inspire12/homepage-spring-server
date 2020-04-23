package com.inspire12.homepage.controller;

import com.inspire12.homepage.model.request.SignupRequest;
import com.inspire12.homepage.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    EmailService emailService;

    public interface MyFunctionalInterface {
        public void method(int x);
    }

    public SignupRequest testSignup () {
        return SignupRequest.create();
    }

    @PostMapping("/test")
    public void sendEmail () {
        emailService.getCertifyTokenByMail("ox4443@naver.com");
    }
}


