package com.inspire12.homepage.controller.community;

import com.inspire12.homepage.common.DefaultValue;
import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.dto.message.ArticleMsg;
import com.inspire12.homepage.message.request.ArticleRequest;
import com.inspire12.homepage.service.board.ArticleService;
import com.inspire12.homepage.service.board.FileMetaService;
import com.inspire12.homepage.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;
    private final FileMetaService fileMetaService;

    @GetMapping("/boards")
    public List<ArticleMsg> showArticle(@RequestParam(value = "size", required = true) int size) {
        return articleService.showArticleMsgs(size);
    }

    @GetMapping("/articles/{id}")
    public ArticleMsg showArticleList(@PathVariable Long id) {
        return articleService.showArticleMsgById(id, DefaultValue.defaultUser().getId());
    }

    @PostMapping("/articles")
    public boolean updateArticle(@Validated @RequestBody ArticleRequest articleRequest) {
        Article article = articleService.updateArticle(articleRequest);
        return true;
    }

    @PutMapping("/articles")
    @Transactional
    public boolean insertArticle(@RequestBody ArticleRequest articleRequest) {

        articleService.saveArticle(articleRequest);
        return true;
    }

    @PutMapping("/articles/replies")
    public boolean insertArticleReply(@RequestBody ArticleRequest requestBody) {
        Article article = Article.createFromRequest(requestBody);
        long parentId = requestBody.getParentId();
        articleService.saveArticleReply(parentId, article);
        return true;
    }

    @DeleteMapping("/articles")
    public boolean deleteArticle(@RequestParam Long id, @RequestHeader HttpHeaders headers) {
        // httpheader;
        return articleService.deleteArticle(id);
    }
}
