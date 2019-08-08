package com.inspire12.homepage.controller.community;


import com.inspire12.homepage.model.Article;
import com.inspire12.homepage.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @GetMapping("/")
    public Article showArticle(@RequestParam String articleId){
        return articleService.showArticle(articleId);
    }


    @GetMapping("/list")
    public List<Article> showArticleList(){
        return articleService.showArticleList();
    }




    @PostMapping("/save")
    public Article saveArticle(@RequestBody Article article){
        return articleService.saveArticle(article);
    }



    @DeleteMapping("/list")
    public boolean deleteArticle(@RequestParam String id, @RequestHeader HttpHeaders headers){
        // httpheader;
        return articleService.deleteArticle(id);
    }

}
