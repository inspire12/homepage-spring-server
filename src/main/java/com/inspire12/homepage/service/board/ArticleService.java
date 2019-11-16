package com.inspire12.homepage.service.board;

import com.inspire12.homepage.model.Article;
import com.inspire12.homepage.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.util.HashMap;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article showArticle(long id){
        return articleRepository.findTopById(id);
    }

    public List<Article> showArticlesByBoard(long boardId){
        return articleRepository.findTop30ByBoardIdOrderByNo(boardId);
    }

    public Article saveArticle(Article article){
        // 데이터 검증
        return articleRepository.save(article);
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
