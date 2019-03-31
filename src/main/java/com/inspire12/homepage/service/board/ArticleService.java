package com.inspire12.homepage.service.board;

import com.inspire12.homepage.model.Article;
import com.inspire12.homepage.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public Article showArticle(String id){
        return articleRepository.findById(id).get();
    }

    public List<Article> showArticleList(){
        return articleRepository.findAll();
    }

    public List<Article> showArticleListByBoard(String boardId){
        return articleRepository.findAllByBoardId(boardId);
    }

    public Article saveArticle(Article article){
        return articleRepository.save(article);
//        return /true;
    }

    public boolean modifyArticle(Article article){
        articleRepository.save(article);
        return true;
    }
    public boolean deleteArticle(String articleId){
        articleRepository.deleteById(articleId);
        return true;
    }
}
