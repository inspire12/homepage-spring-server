package com.inspire12.homepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/list")
    public String getRestBoardList(){
        return "board get";
    }

    @PostMapping("/list")
    public String modifyBoardList(){
        return "board post";
    }

    @PutMapping("/list")
    public String addBoardList(){
        return "board put";
    }

    @DeleteMapping("/list")
    public String deleteBoardList(){
        return "board delete";
    }

}
