package com.inspire12.homepage.service.board;


import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.domain.service.ArticleDomainService;
import com.inspire12.homepage.domain.service.UserDomainService;
import com.inspire12.homepage.dto.user.AppUserInfo;
import com.inspire12.homepage.exception.CommonException;
import com.inspire12.homepage.message.request.ArticleModifyRequest;
import com.inspire12.homepage.message.request.ArticleWriteRequest;
import com.inspire12.homepage.message.response.ArticleInfo;
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
    private final UserDomainService userDomainService;

    @Transactional
    public ArticleInfo showArticleMsgById(Long postId, Long userId) {
        Article article = articleDomainService.getArticleById(postId);
        article.setHits(article.getHits() + 1);
        Boolean articleLike = articleDomainService.getArticleLike(postId, userId);
        return ArticleInfo.create(article, articleLike);
    }

    public List<ArticleInfo> showArticleMsgsWithCount(int type, int pageNum, int articleCount) {
        int start = (pageNum - 1) * articleCount;
        List<Article> articles = articleDomainService.getArticlesByType(type, start, articleCount);
        List<Long> userIds = new ArrayList<>(articleCount);
        for (Article article: articles) {
            userIds.add(article.getAuthorId());
        }
        Map<Long, AppUserInfo> appUserInfoMap = userDomainService.getUserInfoMap(userIds);

        return articles.stream()
                .map(a -> ArticleInfo.createWithUser(a, appUserInfoMap.get(a.getAuthorId())))
                .collect(Collectors.toList());
    }

    public Article updateArticle(ArticleModifyRequest articleModifyRequest) {
        // 데이터 검증
        long id = articleModifyRequest.getId();
        return articleDomainService.saveArticleById(id, articleModifyRequest);
    }

    public List<ArticleInfo> showArticleMsgs(int size) {
        List<Article> articles = articleDomainService.getArticleList(PageRequest.of(0, size));
        List<Long> userIds = new ArrayList<>(size);
        for (Article article: articles) {
            userIds.add(article.getAuthorId());
        }
        Map<Long, AppUserInfo> appUserInfoMap = userDomainService.getUserInfoMap(userIds);

        return articles.stream()
                .map(a -> ArticleInfo.createWithUser(a, appUserInfoMap.getOrDefault(a.getAuthorId(), userDomainService.getSecedeUser())))
                .collect(Collectors.toList());
    }


    @Transactional
    public boolean saveArticleReply(Long parentId, Article childArticle) {
        return articleDomainService.saveArticleReply(parentId, childArticle);
    }

    public boolean deleteArticle(Long articleId) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser appUser = userDomainService.findByUsername(username).orElseThrow(CommonException::new);
        articleDomainService.deleteArticle(articleId, appUser.getId());
        return false;
    }

    public ArticleInfo saveArticle(Long userId, ArticleWriteRequest articleRequest) {
        Article article = Article.of(0, 0,
                articleRequest.getTitle(),
                articleRequest.getContent(),
                userId,
                articleRequest.getBoardId(),
                new ArrayList<>());
        return ArticleInfo.create(articleDomainService.saveArticle(article));
    }
}
