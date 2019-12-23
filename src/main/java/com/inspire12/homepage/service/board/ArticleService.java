package com.inspire12.homepage.service.board;


import com.inspire12.homepage.interceptor.UserLevel;
import com.inspire12.homepage.message.ArticleMsg;
import com.inspire12.homepage.message.CommentMsg;
import com.inspire12.homepage.model.entity.Article;
import com.inspire12.homepage.model.entity.Comment;
import com.inspire12.homepage.model.entity.User;
import com.inspire12.homepage.repository.ArticleRepository;
import com.inspire12.homepage.repository.CommentRepository;
import com.inspire12.homepage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        articleRepository.increaseHit(id);
        return getArticleMsgById(id);
    }

    public ArticleMsg getArticleMsgById(int id) {
        Article article = articleRepository.getOne(id);
        return ArticleMsg.createWithComments(articleRepository.getOne(id), userRepository.getOne(article.getUsername()), convertToMsg(commentRepository.selectCommentByArticleOrder(article.getId())));
    }

    public List<ArticleMsg> showArticleMsgsWithCount(String type, int pageNum, int articleCount) {
        int start = (pageNum-1) * articleCount;
        List<Article> articles;
        if(type.equals("all")) {
            articles = articleRepository.showArticlesWithArticleCount(start, articleCount);
        }else {
            articles = articleRepository.showArticlesWithArticleByTypeCount(type, start, articleCount);
        }

        return convertArticlesToArticleMsgs(articles);
    }


    public List<ArticleMsg> showArticleMsgs() {
        List<Article> articles = articleRepository.selectArticles(30);
        return convertArticlesToArticleMsgs(articles);
    }


    public boolean saveArticle(Article article) {
        // 데이터 검증
        articleRepository.saveArticle(article.getSubject(), article.getContent(), article.getUsername());
        return true;
    }



    @Transactional
    public boolean saveArticleReply(int parentId, Article childArticle) {
        Article parentArticle = articleRepository.findById(parentId).get();
        articleRepository.updateReplyOrder(parentArticle.getGrpno(), parentArticle.getGrpord());
        articleRepository.saveReplyArticle(childArticle.getSubject(), childArticle.getContent(), childArticle.getUsername(),
                    parentArticle.getGrpno(), parentArticle.getGrpord()+1, parentArticle.getDepth() + 1);
        return true;
    }

    public boolean modifyArticle(Article article) {
        articleRepository.save(article);
        return true;
    }

    public boolean deleteArticle(int articleId) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (username.equals(articleRepository.getOne(articleId).getUsername())){
            articleRepository.updateIsDeletedArticle(articleId);
            return true;
        }
        return false;
    }


    private List<ArticleMsg> convertArticlesToArticleMsgs(List<Article> articles) {
        List<ArticleMsg> articleMsgs = new ArrayList<>();
        for (Article article : articles) {
            try {
                User author = userRepository.findById(article.getUsername()).get();
                List<Comment> comments = commentRepository.selectCommentByArticleOrder(article.getId());
                articleMsgs.add(ArticleMsg.createWithComments(article, author, convertToMsg(comments)));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return articleMsgs;
    }

    public List<CommentMsg> convertToMsg(List<Comment> comments) {
        List<CommentMsg> commentMsgs = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            try {
                commentMsgs.add(CommentMsg.createCommentMsg(comments.get(i), userRepository.getOne(comments.get(i).getUsername())));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return commentMsgs;
    }
}
