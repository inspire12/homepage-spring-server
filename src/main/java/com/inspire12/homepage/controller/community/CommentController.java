package com.inspire12.homepage.controller.community;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.homepage.dto.Constant;
import com.inspire12.homepage.dto.message.CommentMsg;
import com.inspire12.homepage.message.request.CommentRequest;
import com.inspire12.homepage.message.response.CommentResponse;
import com.inspire12.homepage.message.response.CommonResponse;
import com.inspire12.homepage.service.board.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PutMapping("/comments")
    public ResponseEntity<CommonResponse<CommentResponse>> saveComment(@RequestBody CommentRequest commentRequest) {

        commentService.saveByRequest(commentRequest);
        List<CommentMsg> comments = commentService.getComments(commentRequest.getArticleId());

        return ResponseEntity.ok(new CommonResponse<>(Constant.SUCCESS, new CommentResponse(comments)));
    }

    // user id
    @PostMapping("/comments")
    public ResponseEntity modifyComment(@RequestBody ObjectNode requestBody) {
        System.out.println(requestBody.get("message"));
//        commentService.saveComment(comment);
        return ResponseEntity.ok().build();
    }

}

