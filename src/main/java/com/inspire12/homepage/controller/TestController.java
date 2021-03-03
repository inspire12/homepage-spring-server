package com.inspire12.homepage.controller;

import com.inspire12.homepage.message.request.ArticleModifyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class TestController {
//    private final EmailService emailService;

//    @PostMapping("/test")
//    public void sendEmail () {
//        emailService.getCertifyTokenByMail("ox4443@naver.com");
//    }

    @PostMapping("/valid")
    @ResponseBody
    public String validTest (@Valid ArticleModifyRequest articleModifyRequest) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<ArticleModifyRequest>> constraintViolations = validator.validate(articleModifyRequest);

        return (articleModifyRequest.getContent());
    }
}


