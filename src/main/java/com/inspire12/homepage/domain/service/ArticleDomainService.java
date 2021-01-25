package com.inspire12.homepage.domain.service;

import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.domain.model.ArticleLike;
import com.inspire12.homepage.domain.model.ArticleLikeId;
import com.inspire12.homepage.domain.repository.ArticleLikeRepository;
import com.inspire12.homepage.domain.repository.ArticleRepository;
import com.inspire12.homepage.domain.repository.CommentRepository;
import com.inspire12.homepage.domain.repository.UserRepository;
import com.inspire12.homepage.exception.CustomException;
import com.inspire12.homepage.message.request.ArticleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleDomainService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleLikeRepository articleLikeRepository;
    private final CommentRepository commentRepository;


    public Article getArticleById(Long postId) {
        return articleRepository.findById(postId).orElseThrow(CustomException::new);
    }

    public List<Article> showArticlesWithCount(int type, int pageNum, int articleCount) {
        int start = (pageNum - 1) * articleCount;
        List<Article> articles;
        if (type == 0) {
            articles = articleRepository.showArticlesWithArticleCount(start, articleCount);
        } else {
            articles = articleRepository.showArticlesWithArticleByTypeCount(type, start, articleCount);
        }
        return articles;
    }

    public Article updateArticle(ArticleRequest articleRequest) {
        // 데이터 검증
        Long id = articleRequest.getId();

        Article article = getArticleById(id);
        article.setSubject(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setBoardId(articleRequest.getType());
        articleRepository.save(article);
        return article;
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Transactional
    public boolean saveArticleReply(Long parentId, Article childArticle) {
        Article parentArticle = getArticleById(parentId);
        articleRepository.updateReplyOrder(parentArticle.getGrpno(), parentArticle.getGrpord());

        childArticle.setGrpno(parentArticle.getGrpno());
        childArticle.setGrpord(parentArticle.getGrpord() + 1);
        childArticle.setDepth(parentArticle.getDepth() + 1);

        articleRepository.save(childArticle);
        return true;
    }

    public boolean deleteArticle(Long articleId, String username) {
         if (username.equals(getArticleById(articleId).getAuthorName())) {
            articleRepository.updateIsDeletedArticle(articleId);
            return true;
        }
        return false;
    }

    public Boolean getArticleLike(Long postId, Long userId) {
        return articleLikeRepository.findById(new ArticleLikeId(postId, userId))
                .map(ArticleLike::getIsLike).orElse(false);
    }

    public List<Article> getArticlesByType(int type, int start, int articleCount) {
        if (type == 0) {
            return articleRepository.showArticlesWithArticleCount(start, articleCount);
        } else {
            return articleRepository.showArticlesWithArticleByTypeCount(type, start, articleCount);
        }
    }

    public Article saveArticleById(long id, ArticleRequest articleRequest) {
        Article article = getArticleById(id);
        article.setSubject(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setBoardId(articleRequest.getType());
        return article;
    }

    public List<Article> selectArticles(PageRequest of) {
        return articleRepository.findAll(of).getContent();
    }
}
