package com.inspire12.homepage.controller.community;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.homepage.message.ArticleMsg;
import com.inspire12.homepage.model.entity.Article;
import com.inspire12.homepage.model.entity.FileMeta;
import com.inspire12.homepage.model.entity.User;
import com.inspire12.homepage.repository.UserRepository;
import com.inspire12.homepage.service.board.ArticleService;
import com.inspire12.homepage.service.board.FileMetaService;
import com.inspire12.homepage.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;

    @Autowired
    FileMetaService fileMetaService;

    @GetMapping("/boards")
    public List<ArticleMsg> showArticle() {
        return articleService.showArticleMsgs();
    }

    @GetMapping("/articles/{id}")
    public ArticleMsg showArticleList(@PathVariable int id) {
        return articleService.getArticleMsgById(id);
    }

    @PostMapping("/articles")
    public boolean updateArticle(@RequestBody ObjectNode requestBody) {
        articleService.updateArticle(requestBody);

        return true;
    }

    @PutMapping("/articles")
    @Transactional
    public boolean insertArticle(@RequestBody ObjectNode requestBody) {
        Article article = Article.createFromRequest(requestBody);
        articleService.saveArticle(article);
        ArrayNode files = (ArrayNode) requestBody.get("files");
        List<FileMeta> fileMetas = new ArrayList<>();
        for(JsonNode file: files){
            FileMeta fileMeta = FileMeta.create(file, article);
            fileMetas.add(fileMeta);
        }
        fileMetaService.saveFileMetas(fileMetas);
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
