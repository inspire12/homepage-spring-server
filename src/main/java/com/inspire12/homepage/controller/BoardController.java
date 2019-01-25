package com.inspire12.homepage.controller;

import org.springframework.web.bind.annotation.*;

@RestController("/board")
public class BoardController {

    @GetMapping("/list")
    public void getRestBoardList(){

    }

    @PostMapping("/list")
    public void modifyBoardList(){

    }

    @PutMapping("/list")
    public void addBoardList(){

    }

    @DeleteMapping("/list")
    public void deleteBoardList(){
        
    }

}
