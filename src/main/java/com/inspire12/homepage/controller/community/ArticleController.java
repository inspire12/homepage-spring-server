package com.inspire12.homepage.controller.community;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.homepage.message.ArticleMsg;
import com.inspire12.homepage.model.entity.Article;
import com.inspire12.homepage.model.entity.User;
import com.inspire12.homepage.repository.UserRepository;
import com.inspire12.homepage.service.board.ArticleService;
import com.inspire12.homepage.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;

    @GetMapping("/boards")
    public List<ArticleMsg> showArticle() {
        return articleService.showArticleMsgs();
    }

    @GetMapping("/articles/{id}")
    public ArticleMsg showArticleList(@PathVariable int id) {
        return articleService.getArticleMsgById(id);
    }

//    @PostMapping("/articles")
//    public boolean saveArticle(@RequestBody Article article) {
//        articleService.saveArticle(article);
//        return true;
//    }

    @PutMapping("/articles")
    public boolean insertArticle(@RequestBody ObjectNode requestBody) {

        Article article = Article.createFromRequest(requestBody);
        articleService.saveArticle(article);
        return true;
    }

    @PutMapping("/articles/replies")
    public boolean insertArticleReply(@RequestBody ObjectNode requestBody) {
        Article article = Article.createFromRequest(requestBody);
        int parentId = requestBody.get("parent_id").asInt();
        articleService.saveArticleReply(parentId, article);
        return true;
    }


    @DeleteMapping("/articles")
    public boolean deleteArticle(@RequestParam int id, @RequestHeader HttpHeaders headers) {
        // httpheader;
        return articleService.deleteArticle(id);
    }
}
