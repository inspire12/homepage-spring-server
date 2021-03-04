package com.inspire12.homepage.controller.community;

import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.exception.CommonException;
import com.inspire12.homepage.message.request.ArticleModifyRequest;
import com.inspire12.homepage.message.request.ArticleWriteRequest;
import com.inspire12.homepage.message.response.ArticleInfo;
import com.inspire12.homepage.service.board.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/boards")
    public List<ArticleInfo> showArticle(@RequestParam(value = "size", required = true) int size) {
        return articleService.showArticleMsgs(size);
    }

    @GetMapping("/articles/{id}")
    public ArticleInfo showArticleList(HttpSession session, @PathVariable Long id) {
        AppUser user = (AppUser) session.getAttribute("user");
        Long userId = 0L;
        if (!Objects.isNull(user)) {
            userId = user.getId();
        }
        return articleService.showArticleMsgById(id, userId);
    }

    @PostMapping("/articles/write")
    public void writeArticle(HttpSession session, @RequestBody ArticleWriteRequest articleRequest) {
        AppUser user = (AppUser) session.getAttribute("user");
        if (Objects.isNull(user)) {
            throw new CommonException();
        }
        articleService.saveArticle(user.getId(), articleRequest);
    }

    @PostMapping("/articles/modify")
    public void modifyArticle(@RequestBody ArticleModifyRequest articleModifyRequest) {
        articleService.updateArticle(articleModifyRequest);
    }

    @PutMapping("/articles/replies")
    public void insertArticleReply(@RequestBody ArticleModifyRequest requestBody) {
        long parentId = requestBody.getParentId();
        articleService.saveArticleReply(parentId);
    }

    @DeleteMapping("/articles")
    public boolean deleteArticle(@RequestParam Long id, @RequestHeader HttpHeaders headers) {
        return articleService.deleteArticle(id);
    }
}
