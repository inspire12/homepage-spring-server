package com.inspire12.homepage.controller;

import com.inspire12.homepage.model.request.Signup;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.ResponseMessage;

@Controller
public class TestController {

    public interface MyFunctionalInterface {
        public void method(int x);
    }

    public Signup testSignup () {
        return Signup.create();
    }
}


