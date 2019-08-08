//package com.inspire12.homepage.controller.community;
//
//import com.inspire12.homepage.model.Board;
//import com.inspire12.homepage.repository.BoardRepository;
//import com.inspire12.homepage.service.BoardService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/board")
//public class BoardController {
//
//    @Autowired
//    BoardService boardService;
//
//    @GetMapping("/list")
//    public List<Board> getRestBoardList(){
//        return boardService.findBoardList();
//    }
//
//    @PostMapping("/update")
//    public Board modifyBoardList(@RequestBody Board board){
//        return boardService.save(board);
//    }
//
//
//
//}
