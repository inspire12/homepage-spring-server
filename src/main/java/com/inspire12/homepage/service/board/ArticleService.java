package com.inspire12.homepage.service.board;

import com.inspire12.homepage.message.ArticleMsg;
import com.inspire12.homepage.model.Article;
import com.inspire12.homepage.model.User;
import com.inspire12.homepage.repository.ArticleRepository;
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

    public ArticleMsg showArticleMsg(int id) {
        Article article = articleRepository.getOne(id);
        return ArticleMsg.create(articleRepository.getOne(id), userRepository.getOne(article.getUserId()));
    }

    public List<ArticleMsg> showArticlesByBoard(int boardId) {
        List<Article> articles = articleRepository.findTop30ByBoardIdOrderByNoDesc(boardId);
        return convertArticlesToArticleMsgs(articles);
    }

    public List<ArticleMsg> showArticles(int articleCount) {
        List<Article> articles = articleRepository.showArticlesInBoard(articleCount);
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


    private List<ArticleMsg> convertArticlesToArticleMsgs(List<Article> articles){
        List<ArticleMsg> articleMsgs = new ArrayList<>();
        for (Article article : articles) {
            User author = userRepository.findById(article.getUserId()).get();
            articleMsgs.add(ArticleMsg.create(article, author));
        }
        return articleMsgs;
    }
}
