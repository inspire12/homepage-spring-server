package com.inspire12.homepage.service.board;


import com.inspire12.homepage.message.ArticleMsg;
import com.inspire12.homepage.message.CommentMsg;
import com.inspire12.homepage.model.entity.Article;
import com.inspire12.homepage.model.entity.Comment;
import com.inspire12.homepage.model.entity.User;
import com.inspire12.homepage.repository.ArticleRepository;
import com.inspire12.homepage.repository.CommentRepository;
import com.inspire12.homepage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    public ArticleMsg showArticleMsgById(int id) {
        Article article = articleRepository.getOne(id);
        return ArticleMsg.createWithComments(articleRepository.getOne(id), userRepository.getOne(article.getUserId()), convertToMsg(commentRepository.findAllByArticleId(article.getId())));
    }

    public List<ArticleMsg> showArticleMsgsWithCount(int articleCount) {
        List<Article> articles = articleRepository.showArticlesWithArticleCount(articleCount);
        return convertArticlesToArticleMsgs(articles);
    }

    public List<ArticleMsg> showArticleMsgs() {
        List<Article> articles = articleRepository.findAll();
        return convertArticlesToArticleMsgs(articles);
    }


    public boolean saveArticle(Article article) {
        // 데이터 검증
        articleRepository.save(article);
        return true;
    }

    public boolean modifyArticle(Article article) {
        articleRepository.save(article);
        return true;
    }

    public boolean deleteArticle(int articleId) {
        articleRepository.deleteById(articleId);
        return true;
    }


    private List<ArticleMsg> convertArticlesToArticleMsgs(List<Article> articles) {
        List<ArticleMsg> articleMsgs = new ArrayList<>();
        for (Article article : articles) {
            User author = userRepository.findById(article.getUserId()).get();
            List<Comment> comments = commentRepository.findAllByArticleId(article.getId());
            List<CommentMsg> commentMsgs = convertToMsg(comments);
            ArticleMsg articleMsg = ArticleMsg.createWithComments(article, author, commentMsgs);
            articleMsgs.add(articleMsg);
        }
        return articleMsgs;
    }

    public List<CommentMsg> convertToMsg(List<Comment> comments) {
        List<CommentMsg> commentMsgs = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            Comment comment = comments.get(i);
            User user = userRepository.getOne(comments.get(i).getUserId());
            CommentMsg commentMsg = CommentMsg.createCommentMsg(comment, user);
            commentMsgs.add(commentMsg);
        }
        return commentMsgs;
    }
}
