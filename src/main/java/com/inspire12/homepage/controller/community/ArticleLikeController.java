package com.inspire12.homepage.controller.community;

import com.inspire12.homepage.exception.CommonException;
import com.inspire12.homepage.exception.NotAuthException;
import com.inspire12.homepage.message.response.CommonResponse;
import com.inspire12.homepage.dto.Constant;
import com.inspire12.homepage.service.board.ArticleLikeService;
import com.inspire12.homepage.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleLikeController {

    private final ArticleLikeService articleLikeService;
    private final UserService userService;

    @PostMapping("/likes/{postId}")
    public ResponseEntity<CommonResponse<Boolean>> incArticleLike(@PathVariable(name = "postId") Long postId) throws NotAuthException {
        if(! SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new NotAuthException();
        }
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        articleLikeService.increaseArticleLike(postId, userService.getUser(username).orElseThrow(CommonException::new).getId());
        return ResponseEntity.ok(new CommonResponse<>(Constant.SUCCESS, true));
    }

    @DeleteMapping("/likes/{postId}")
    public ResponseEntity<CommonResponse<Boolean>> decArticleLike(@PathVariable(name = "postId") Long postId) throws NotAuthException {
        if(! SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new NotAuthException();
        }
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        articleLikeService.decreaseArticleLike(postId, userService.getUser(username).orElseThrow(CommonException::new).getId());
        return ResponseEntity.ok(new CommonResponse<>(Constant.SUCCESS, true));
    }
}
