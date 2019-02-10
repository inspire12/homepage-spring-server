package com.inspire12.homepage.repository;

import com.inspire12.homepage.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {
    List<Article> findAllByBoardId(String boardId);

}
