package com.inspire12.homepage.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public String showArticle(){
        return "article get";
    }

    @PostMapping("/list")
    public String poArticle(){
        return "article";
    }

    @PutMapping("/list")
    public String putArticle(){
        return "article put";
    }

    @DeleteMapping("/list")
    public String deleteArticle(){
        return "article delete";
    }

}
