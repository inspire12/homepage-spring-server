package com.inspire12.homepage.controller.community;

import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.exception.CommonException;
import com.inspire12.homepage.message.request.ArticleModifyRequest;
import com.inspire12.homepage.message.request.ArticleWriteRequest;
import com.inspire12.homepage.message.response.ArticleInfo;
import com.inspire12.homepage.message.viewmodel.ArticleSaveResponse;
import com.inspire12.homepage.service.board.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List<ArticleInfo> showArticleList(@PageableDefault PageRequest pageRequest) {
        return articleService.showArticleMsgs(pageRequest.getPageSize());
    }

    @GetMapping("/articles/{id}")
    public ArticleInfo showArticle(HttpSession session, @PathVariable Long id) {
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
    public ArticleSaveResponse modifyArticle(HttpSession session, @RequestBody ArticleModifyRequest articleModifyRequest) {
        return articleService.updateArticle(articleModifyRequest);
    }

    @DeleteMapping("/articles")
    public ArticleSaveResponse deleteArticle(@RequestParam Long id, @RequestHeader HttpHeaders headers) {
        return articleService.deleteArticle(id);
    }
}
