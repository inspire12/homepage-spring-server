package com.inspire12.homepage.service.board;


import com.inspire12.homepage.assembler.ArticleAssembler;
import com.inspire12.homepage.common.DefaultValue;
import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.domain.model.Comment;
import com.inspire12.homepage.domain.service.ArticleDomainService;
import com.inspire12.homepage.dto.message.ArticleMsg;
import com.inspire12.homepage.dto.message.CommentMsg;
import com.inspire12.homepage.message.request.ArticleRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleDomainService articleDomainService;

    @Transactional
    public ArticleMsg showArticleMsgById(Long postId, Long userId) {
        Article article = articleDomainService.getArticleById(postId);
        article.setHit(article.getHit() + 1);
        Boolean articleLike = articleDomainService.getArticleLike(postId, userId);
        return ArticleMsg.create(article, articleLike);
    }

    public List<ArticleMsg> showArticleMsgsWithCount(int type, int pageNum, int articleCount) {
        int start = (pageNum - 1) * articleCount;
        List<Article> articles = articleDomainService.getArticlesByType(type, start, articleCount);
        return ArticleAssembler.convertArticles(articles);
    }

    public Article updateArticle(ArticleRequest articleRequest) {
        // 데이터 검증
        long id = articleRequest.getId();
        return articleDomainService.saveArticleById(id, articleRequest);
    }

    public List<ArticleMsg> showArticleMsgs(int size) {
        List<Article> articles = articleDomainService.selectArticles(PageRequest.of(0, size));
        return ArticleAssembler.convertArticles(articles);
    }


    @Transactional
    public boolean saveArticleReply(Long parentId, Article childArticle) {
        return articleDomainService.saveArticleReply(parentId, childArticle);
    }

    public boolean deleteArticle(Long articleId) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        articleDomainService.deleteArticle(articleId, username);
        return false;
    }

    public ArticleMsg saveArticle(ArticleRequest articleRequest) {
        return ArticleMsg.create(articleDomainService.saveArticle(Article.createFromRequest(articleRequest)));
    }

    private List<CommentMsg> convertToMsg(List<Comment> comments, Map<Long, AppUser> userMap) {
        List<CommentMsg> commentMsgs = new ArrayList<>();
        for (Comment comment : comments) {
            try {
                commentMsgs.add(CommentMsg.createCommentMsg(comment, userMap.getOrDefault(comment.getArticleId(), DefaultValue.defaultUser())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return commentMsgs;
    }
}
