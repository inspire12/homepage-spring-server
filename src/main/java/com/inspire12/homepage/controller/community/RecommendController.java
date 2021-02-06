package com.inspire12.homepage.controller.community;

import com.inspire12.homepage.aspect.MethodAllow;
import com.inspire12.homepage.message.response.CommonResponse;
import com.inspire12.homepage.service.board.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecommendController {

    private final RecommendService recommendService;

    @MethodAllow(allow = MethodAllow.UserRole.USER)
    @PostMapping("/recommend")
    public ResponseEntity<CommonResponse<Boolean>> recommendArticle(@RequestParam Long articleId, @RequestParam String username) {
        boolean isSuccess = recommendService.recommendToggleArticle(articleId, username);
        return ResponseEntity.ok( new CommonResponse<Boolean>(1, isSuccess));
    }
}
