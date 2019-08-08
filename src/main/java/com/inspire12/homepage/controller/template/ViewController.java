package com.inspire12.homepage.controller.template;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class ViewController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
