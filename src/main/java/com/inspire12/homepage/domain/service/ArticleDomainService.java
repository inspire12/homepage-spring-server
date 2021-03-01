package com.inspire12.homepage.domain.service;

import com.inspire12.homepage.common.LikeType;
import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.domain.model.UserLike;
import com.inspire12.homepage.domain.model.UserLikeId;
import com.inspire12.homepage.domain.repository.ArticleRepository;
import com.inspire12.homepage.domain.repository.UserLikeRepository;
import com.inspire12.homepage.exception.CommonException;
import com.inspire12.homepage.message.request.ArticleModifyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleDomainService {
    private final ArticleRepository articleRepository;
    private final UserLikeRepository userLikeRepository;

    public Article getArticleById(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(CommonException::new);
    }

    public List<Article> showArticleListWithCount(int type, int pageNum, int articleCount) {
        int start = (pageNum - 1) * articleCount;
        List<Article> articles;
        if (type == 0) {
            articles = articleRepository.findByDeletedIsFalse(PageRequest.of(start, articleCount)).getContent();
        } else {
            articles = articleRepository.findByBoardIdAndDeletedIsFalse(type, PageRequest.of(pageNum, articleCount)).getContent();
        }
        return articles;
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Transactional
    public boolean saveArticleReply(Long parentId, Article childArticle) {
        Article parentArticle = getArticleById(parentId);
        List<Article> articles = articleRepository.findByGrpNoAndGrpOrderBefore(parentArticle.getGrpNo(), parentArticle.getGrpOrder());
        for (Article article: articles) {
            article.setGrpOrder(article.getGrpOrder() + 1);
        }
        parentArticle.setGrpOrder(parentArticle.getGrpOrder() + 1);
        childArticle.setGrpNo(parentArticle.getGrpNo());
        childArticle.setGrpOrder(parentArticle.getGrpOrder() + 1);
//        childArticle.setDepth(parentArticle.getDepth() + 1);

        articleRepository.save(childArticle);
        return true;
    }

    public boolean deleteArticle(Long articleId, Long userId) {
        Article article = getArticleById(articleId);
        if (userId.equals(article.getAuthorId())) {
            article.setDeleted(true);
            return true;
        }
        return false;
    }

    public Boolean getArticleLike(Long postId, Long userId) {
        return userLikeRepository.findById(new UserLikeId(postId, userId, LikeType.ARTICLE))
                .map(UserLike::isLiked).orElse(false);
    }

    public List<Article> getArticlesByType(int type, int start, int articleCount) {
        if (type == 0) {
            return articleRepository.findByDeletedIsFalse(PageRequest.of(start, articleCount)).getContent();
        } else {
            return articleRepository.findByBoardIdAndDeletedIsFalse(type, PageRequest.of(start, articleCount)).getContent();
        }
    }

    public Article saveArticleById(long id, ArticleModifyRequest articleModifyRequest) {
        Article article = getArticleById(id);
        article.setTitle(articleModifyRequest.getTitle());
        article.setContent(articleModifyRequest.getContent());
        article.setBoardId(articleModifyRequest.getBoardId());
        return article;
    }

    public List<Article> getArticleList(PageRequest pageRequest) {
        return articleRepository.findAll(pageRequest).getContent();
    }
}
