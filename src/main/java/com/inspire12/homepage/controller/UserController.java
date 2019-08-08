//package com.inspire12.homepage.controller;
//
//import com.inspire12.homepage.model.User;
//import com.inspire12.homepage.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import sun.misc.Request;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    UserService userService;
//
//    @GetMapping("/list")
//    public List<User> accessUsers(){
//        return userService.listUp();
//    }
//
//    @GetMapping("/access")
//    public User accessUser(@RequestParam String id) {
//        return userService.findUser(id);
//    }
//
//    @PostMapping("/signUp")
//    public boolean updateUser(@RequestBody User user){
//        return userService.createUser(user);
//    }
//
//    @PutMapping("/list")
//    public boolean signUpUser(@RequestBody User user){
//        return userService.createUser(user);
//    }
//
//    @DeleteMapping("/deleteUser")
//    public boolean show(@RequestHeader HttpHeaders httpHeaders){
//
//        String userId = httpHeaders.get("userId").get(0);
//        String passwd = httpHeaders.get("passwd").get(0);
//        return userService.deleteUser(userId, passwd);
//    }
//
//
//
//}
