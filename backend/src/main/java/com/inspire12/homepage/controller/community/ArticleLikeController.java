package com.inspire12.homepage.controller.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.homepage.exception.NotAuthException;
import com.inspire12.homepage.service.board.ArticleLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleLikeController {

    @Autowired
    ArticleLikeService articleLikeService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/likes/{postId}")
    public ResponseEntity<ObjectNode> incArticleLike(@PathVariable(name = "postId") Long postId) throws NotAuthException {
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new NotAuthException();
        }
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        articleLikeService.incArticleLike(postId, username);
        ObjectNode response = objectMapper.createObjectNode();
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/likes/{postId}")
    public ResponseEntity<ObjectNode> decArticleLike(@PathVariable(name = "postId") Long postId) throws NotAuthException {
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new NotAuthException();
        }
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        articleLikeService.decArticleLike(postId, username);
        ObjectNode response = objectMapper.createObjectNode();
        response.put("status", true);
        return ResponseEntity.ok(response);
    }
}
