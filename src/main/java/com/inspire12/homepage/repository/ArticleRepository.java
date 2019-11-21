package com.inspire12.homepage.repository;

import com.inspire12.homepage.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findTop30ByBoardIdOrderByNo(int boardId);

    @Query(value = "select * from article order by no", nativeQuery = true)
    List<Article> findByOrderByNo();

    @Query("select a.userId from Article a where a.userId=:userId")
    List<Article> selectAll(@Param("userId") long userId);
}
