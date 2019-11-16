package com.inspire12.homepage.controller.community;


import com.inspire12.homepage.model.Article;
import com.inspire12.homepage.service.board.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @GetMapping("/boards")
    public List<Article> showArticle(@RequestParam(value = "id",defaultValue = "0") long boardId){
        return articleService.showArticlesByBoard(boardId);
    }


    @GetMapping("/articles/{id}")
    public Article showArticleList(@PathVariable long id){
        return articleService.showArticle(id);
    }

    @PostMapping("/articles")
    public Article saveArticle(@RequestBody Article article){

        return articleService.saveArticle(article);
    }

    @DeleteMapping("/articles")
        public boolean deleteArticle(@RequestParam String id, @RequestHeader HttpHeaders headers){
            // httpheader;
            return articleService.deleteArticle(id);
    }

}
