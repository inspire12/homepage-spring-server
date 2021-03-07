package com.inspire12.homepage.service.board;


import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.domain.service.ArticleDomainService;
import com.inspire12.homepage.dto.article.CommentInfo;
import com.inspire12.homepage.dto.user.AppUserInfo;
import com.inspire12.homepage.exception.CommonException;
import com.inspire12.homepage.exception.DataNotFoundException;
import com.inspire12.homepage.message.request.ArticleModifyRequest;
import com.inspire12.homepage.message.request.ArticleWriteRequest;
import com.inspire12.homepage.message.response.ArticleInfo;
import com.inspire12.homepage.message.viewmodel.ArticleSaveResponse;
import com.inspire12.homepage.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleDomainService articleDomainService;
    private final CommentService commentService;
    private final UserService userService;

    @Transactional
    public ArticleInfo getArticleMsgById(Long articleId, Long userId) {
        Article article = articleDomainService.getArticleById(articleId);
        article.setHits(article.getHits() + 1);
        AppUserInfo appUserInfo = AppUserInfo.create(userService.getUserById(article.getAuthorId())
                .orElseThrow(DataNotFoundException::new));
        List<CommentInfo> comments = commentService.getComments(articleId, 100);
        return ArticleInfo.createWithComments(article,
                appUserInfo,
                comments);
    }

    @Transactional(readOnly = true)
    public List<ArticleInfo> getArticleMsgsWithCount(String type, Pageable pageRequest) {
        List<Article> articles = articleDomainService.getArticlesByType(type, pageRequest);

        List<Long> userIds = new ArrayList<>(pageRequest.getPageSize());
        for (Article article : articles) {
            userIds.add(article.getAuthorId());
        }
        Map<Long, AppUserInfo> appUserInfoMap = userService.getUserInfoMap(userIds);

        return articles.stream()
                .map(a -> ArticleInfo.create(a, appUserInfoMap.get(a.getAuthorId())))
                .collect(Collectors.toList());
    }

    public ArticleSaveResponse updateArticle(ArticleModifyRequest articleModifyRequest) {
        // 데이터 검증
        long id = articleModifyRequest.getId();
        Article article = articleDomainService.saveArticleById(id, articleModifyRequest);
        return new ArticleSaveResponse(article.getBoardType(), article.getId());
    }

    @Transactional(readOnly = true)
    public List<ArticleInfo> getArticleMsgs(PageRequest pageRequest) {
        List<Article> articles = articleDomainService.getArticleList(pageRequest);
        List<Long> userIds = new ArrayList<>(pageRequest.getPageSize());
        for (Article article : articles) {
            userIds.add(article.getAuthorId());
        }
        Map<Long, AppUserInfo> appUserInfoMap = userService.getUserInfoMap(userIds);

        return articles.stream()
                .map(a -> ArticleInfo.create(a, appUserInfoMap.getOrDefault(a.getAuthorId(), userService.getSecedeUser())))
                .collect(Collectors.toList());
    }


    @Transactional
    public boolean saveArticleReply(Long parentId) {
        Article childArticle = articleDomainService.getArticleById(parentId);
        return articleDomainService.saveArticleReply(parentId, childArticle);
    }

    public ArticleSaveResponse deleteArticle(Long articleId) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser appUser = userService.getUserByName(username).orElseThrow(CommonException::new);
        Article article = articleDomainService.deleteArticle(articleId, appUser.getId());
        return new ArticleSaveResponse(article.getBoardType(), articleId);
    }

    @Transactional
    public ArticleSaveResponse saveArticle(Long userId, ArticleWriteRequest articleRequest) {
        Article newArticle = Article.of(0, 0,
                articleRequest.getTitle(),
                articleRequest.getContent(),
                userId,
                articleRequest.getBoardType(),
                new ArrayList<>());
        newArticle = articleDomainService.saveArticle(newArticle);
        return new ArticleSaveResponse(newArticle.getBoardType(), newArticle.getId());
    }
}
