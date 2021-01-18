package com.inspire12.homepage.service.board;


import com.inspire12.homepage.common.DefaultValue;
import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.domain.model.ArticleLike;
import com.inspire12.homepage.domain.model.ArticleLikeId;
import com.inspire12.homepage.domain.model.Comment;
import com.inspire12.homepage.domain.repository.ArticleLikeRepository;
import com.inspire12.homepage.domain.repository.ArticleRepository;
import com.inspire12.homepage.domain.repository.CommentRepository;
import com.inspire12.homepage.domain.repository.UserRepository;
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
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    private final ArticleLikeRepository articleLikeRepository;

    private final CommentRepository commentRepository;

    @Transactional()
    public ArticleMsg showArticleMsgById(Long postId) {
        articleRepository.increaseHit(postId);
        return getArticleMsgById(postId);
    }


    public ArticleMsg getArticleMsgById(Long postId) {
        Article article = articleRepository.findById(postId).get();
        Optional<ArticleLike> articleLike = articleLikeRepository.findById(new ArticleLikeId(postId, (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        if (article.getIsDeleted()) {

        }

        return ArticleMsg.create(article, articleLike.isPresent());
    }

    public List<ArticleMsg> showArticleMsgsWithCount(int type, int pageNum, int articleCount) {
        int start = (pageNum - 1) * articleCount;
        List<Article> articles;
        if (type == 0) {
            articles = articleRepository.showArticlesWithArticleCount(start, articleCount);
        } else {
            articles = articleRepository.showArticlesWithArticleByTypeCount(type, start, articleCount);
        }
        return convertArticles(articles);
    }

    public Article updateArticle(ArticleRequest articleRequest) {
        // 데이터 검증
        long id = articleRequest.getId();

        Article article = articleRepository.findById(id).get();
        article.setSubject(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setBoardId(articleRequest.getType());
        articleRepository.save(article);
        return article;
    }

    public List<ArticleMsg> showArticleMsgs(int size) {
        List<Article> articles = articleRepository.selectArticles(PageRequest.of(0, size));
        return convertArticles(articles);
    }

    public boolean saveArticle(Article article) {
        // 데이터 검증
        articleRepository.save(article);
        article.setGrpno(article.getGrpno());
        articleRepository.save(article);
        return true;
    }


    @Transactional
    public boolean saveArticleReply(Long parentId, Article childArticle) {
        Article parentArticle = articleRepository.findById(parentId).get();
        articleRepository.updateReplyOrder(parentArticle.getGrpno(), parentArticle.getGrpord());

        childArticle.setGrpno(parentArticle.getGrpno());
        childArticle.setGrpord(parentArticle.getGrpord() + 1);
        childArticle.setDepth(parentArticle.getDepth() + 1);

        articleRepository.save(childArticle);
        return true;
    }

    public boolean modifyArticle(Article article) {
        articleRepository.save(article);
        return true;
    }

    public boolean deleteArticle(Long articleId) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (username.equals(articleRepository.getOne(articleId).getUsername())) {
            articleRepository.updateIsDeletedArticle(articleId);
            return true;
        }
        return false;
    }

    private List<ArticleMsg> convertArticles(List<Article> articles) {
        List<ArticleMsg> articleMsgs = new ArrayList<>();
        for (Article article : articles) {
            try {
                articleMsgs.add(ArticleMsg.create(article));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return articleMsgs;
    }

    private List<ArticleMsg> convertArticlesToArticleMsgs(List<Article> articles) {
        List<ArticleMsg> articleMsgs = new ArrayList<>();
        for (Article article : articles) {
            try {
                AppUser author = userRepository.findById(article.getId()).get();
                List<Comment> comments = commentRepository.selectCommentByArticleOrder(article.getId());
                articleMsgs.add(ArticleMsg.createWithComments(article, author, convertToMsg(comments)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return articleMsgs;
    }

    private List<CommentMsg> convertToMsg(List<Comment> comments) {
        List<CommentMsg> commentMsgs = new ArrayList<>();
        for (Comment comment : comments) {
            try {
                commentMsgs.add(CommentMsg.createCommentMsg(comment, userRepository.findById(comment.getId()).orElseGet(DefaultValue::defaultUser)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return commentMsgs;
    }
}
