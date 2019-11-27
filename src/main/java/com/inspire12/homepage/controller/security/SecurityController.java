package com.inspire12.homepage.controller.security;

import io.swagger.models.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/login")
    public String getLogin(Model model){
        return "/auth/login";
    }
}
