package com.inspire12.homepage.controller.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.homepage.interceptor.UserLevel;
import com.inspire12.homepage.service.board.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    @Autowired
    ObjectMapper objectMapper;

    @UserLevel(allow = UserLevel.UserRole.USER)
    @PostMapping("recommend")
    public ResponseEntity<ObjectNode> recommendArticle(@RequestParam Integer articleId, @RequestParam String username) {
        boolean isSuccess = recommendService.recommendToggleArticle(articleId, username);
        ObjectNode response = objectMapper.createObjectNode();
        response.put("is_success", isSuccess);
        return ResponseEntity.ok(response);
    }
}
