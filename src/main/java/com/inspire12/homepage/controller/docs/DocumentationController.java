package com.inspire12.homepage.controller.docs;

import com.inspire12.homepage.exception.CommonException;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/documents")
@ApiIgnore
public class DocumentationController {

    @Value
    public static class ErrorMessage {
        int errorCode;
        String defined;
        HttpStatus httpStatus;
        String messageEn;
        String messageKo;
    }

    @GetMapping("/error/sample")
    public void getErrorSample() {
        throw new CommonException();
    }
}
