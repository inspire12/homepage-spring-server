package com.inspire12.homepage.repository;

import com.inspire12.homepage.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findTop30ByBoardIdOrderByNoDesc(int boardId);

    @Query(value = "select * from article order by `no` desc limit :articleCount", nativeQuery = true)
    List<Article> showArticlesWithArticleCount(@Param(value = "articleCount") int articleCount);


    @Query(value = "SELECT a.*, u.nickname FROM homepage.article as a join homepage.user as u on a.user_id=u.id order by `no` limit 30", nativeQuery = true)
    List<Tuple> selectArticlesWithNickname();

}
