package com.inspire12.homepage.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.ResponseMessage;

@RestController
public class TestController {



    public interface MyFunctionalInterface {
        public void method(int x);
    }
}


