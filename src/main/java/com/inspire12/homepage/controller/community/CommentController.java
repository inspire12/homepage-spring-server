package com.inspire12.homepage.controller.community;

import com.inspire12.homepage.dto.Constant;
import com.inspire12.homepage.dto.article.CommentInfo;
import com.inspire12.homepage.message.request.CommentModifyRequest;
import com.inspire12.homepage.message.request.CommentRequest;
import com.inspire12.homepage.message.response.CommentListResponse;
import com.inspire12.homepage.message.response.CommonResponse;
import com.inspire12.homepage.service.board.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PutMapping("/comments")
    public ResponseEntity<CommonResponse<CommentListResponse>> saveComment(@RequestBody CommentRequest commentRequest, @RequestParam(defaultValue = "10") @Max(30) Integer count) {
        commentService.saveByRequest(commentRequest);
        List<CommentInfo> comments = commentService.getComments(commentRequest.getArticleId(), count);
        return ResponseEntity.ok(new CommonResponse<>(Constant.SUCCESS, new CommentListResponse(comments)));
    }

    // user id
    @PostMapping("/comments")
    public ResponseEntity modifyComment(@RequestBody CommentModifyRequest requestBody) {
//        commentService.saveComment(comment);
        return ResponseEntity.ok().build();
    }

}

