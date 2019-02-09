package com.inspire12.homepage.controller;

import com.inspire12.homepage.model.User;
import com.inspire12.homepage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public String accessUsers(){
        return "User list";
    }

    @GetMapping("/one")
    public User accessUser(@RequestParam String id) {
        return userService.findUser(id);
    }

    @PostMapping("/list")
    public String updateUser(){
        return "User post";
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
