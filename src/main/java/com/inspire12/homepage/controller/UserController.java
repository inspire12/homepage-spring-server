package com.inspire12.homepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public String accessUser(){
        return "user list";
    }

    @PostMapping("/list")
    public String updateUser(){
        return "user post";
    }

    @PutMapping("/list")
    public String signUpUser(){
        return "helloo world";
    }

    @DeleteMapping("/deleteUser")
    public String show(){
        return "helloo world";
    }

}
