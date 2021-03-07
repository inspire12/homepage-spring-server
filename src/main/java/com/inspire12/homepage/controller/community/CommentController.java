package com.inspire12.homepage.controller.community;

import com.inspire12.homepage.aspect.MethodAllow;
import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.dto.Constant;
import com.inspire12.homepage.dto.article.CommentInfo;
import com.inspire12.homepage.message.request.CommentRequest;
import com.inspire12.homepage.message.response.CommentListResponse;
import com.inspire12.homepage.message.response.CommonResponse;
import com.inspire12.homepage.service.board.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @MethodAllow(allow = MethodAllow.UserRole.USER)
    @PostMapping(value = "/comments")
    @ResponseBody
    public CommonResponse<CommentListResponse> saveComment(HttpSession httpSession,
                                                                           @RequestBody CommentRequest commentRequest,
                                                                           @RequestParam(defaultValue = "10") @Max(30) Integer count) {
        AppUser user = (AppUser) httpSession.getAttribute("user");

        commentService.saveComment(user, commentRequest);
        List<CommentInfo> comments = commentService.getComments(commentRequest.getArticleId(), count);
        return new CommonResponse<>(Constant.SUCCESS, new CommentListResponse(comments));
    }

    @MethodAllow(allow = MethodAllow.UserRole.USER)
    @PostMapping(value = "/comments/modify")
    @ResponseBody
    public CommonResponse<CommentListResponse> modifyComment(HttpSession httpSession,
                                                           @RequestBody CommentRequest commentRequest,
                                                           @RequestParam(defaultValue = "10") @Max(30) Integer count) {
        AppUser user = (AppUser) httpSession.getAttribute("user");

        commentService.saveComment(user, commentRequest);
        List<CommentInfo> comments = commentService.getComments(commentRequest.getArticleId(), count);
        return new CommonResponse<>(Constant.SUCCESS, new CommentListResponse(comments));
    }
}

