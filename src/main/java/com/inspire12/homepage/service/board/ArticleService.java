package com.inspire12.homepage.service.board;


import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.domain.service.ArticleDomainService;
import com.inspire12.homepage.dto.user.AppUserInfo;
import com.inspire12.homepage.exception.CommonException;
import com.inspire12.homepage.message.request.ArticleModifyRequest;
import com.inspire12.homepage.message.request.ArticleWriteRequest;
import com.inspire12.homepage.message.response.ArticleInfo;
import com.inspire12.homepage.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    private final UserService userService;

    @Transactional(readOnly = true)
    public ArticleInfo showArticleMsgById(Long postId, Long userId) {
        Article article = articleDomainService.getArticleById(postId);
        article.setHits(article.getHits() + 1);
        boolean articleLike = userId != 0 && articleDomainService.getArticleLike(postId, userId);
        AppUserInfo appUserInfo = AppUserInfo.create(userService.getUserById(article.getAuthorId())
                .orElseThrow(() -> new CommonException()));
        return ArticleInfo.create(article,
                appUserInfo,
                articleLike);
    }

    @Transactional(readOnly = true)
    public List<ArticleInfo> showArticleMsgsWithCount(String type, int pageNum, int articleCount) {
        int start = (pageNum - 1) * articleCount;
        List<Article> articles = articleDomainService.getArticlesByType(type, start, articleCount);
        List<Long> userIds = new ArrayList<>(articleCount);
        for (Article article : articles) {
            userIds.add(article.getAuthorId());
        }
        Map<Long, AppUserInfo> appUserInfoMap = userService.getUserInfoMap(userIds);

        return articles.stream()
                .map(a -> ArticleInfo.create(a, appUserInfoMap.get(a.getAuthorId())))
                .collect(Collectors.toList());
    }

    public Article updateArticle(ArticleModifyRequest articleModifyRequest) {
        // 데이터 검증
        long id = articleModifyRequest.getId();
        return articleDomainService.saveArticleById(id, articleModifyRequest);
    }

    @Transactional(readOnly = true)
    public List<ArticleInfo> showArticleMsgs(int size) {
        List<Article> articles = articleDomainService.getArticleList(PageRequest.of(0, size));
        List<Long> userIds = new ArrayList<>(size);
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

    public boolean deleteArticle(Long articleId) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser appUser = userService.getUserByName(username).orElseThrow(CommonException::new);
        articleDomainService.deleteArticle(articleId, appUser.getId());
        return false;
    }

    @Transactional
    public void saveArticle(Long userId, ArticleWriteRequest articleRequest) {
        Article article = Article.of(0, 0,
                articleRequest.getTitle(),
                articleRequest.getContent(),
                userId,
                articleRequest.getBoardType(),
                new ArrayList<>());
        articleDomainService.saveArticle(article);
    }
}
