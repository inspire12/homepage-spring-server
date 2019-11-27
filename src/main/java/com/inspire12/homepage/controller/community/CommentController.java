package com.inspire12.homepage.controller.community;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.homepage.model.entity.Comment;
import com.inspire12.homepage.service.board.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @CrossOrigin(origins = "http://cnuant.iptime.org:8080")
    @PutMapping("/comments")
    public ResponseEntity saveComment(@RequestBody ObjectNode requestBody) {

        commentService.saveByRequest(requestBody);
        return ResponseEntity.ok().build();
    }

    // user id
    @PostMapping("/comments")
    public ResponseEntity modifyComment(@RequestBody ObjectNode requestBody) {
        System.out.println(requestBody.get("message"));
//        commentService.saveComment(comment);
        return ResponseEntity.ok().build();
    }

}

