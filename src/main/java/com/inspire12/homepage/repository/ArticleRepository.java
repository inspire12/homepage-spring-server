//package com.inspire12.homepage.repository;
//
//import com.inspire12.homepage.model.Article;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import javax.persistence.Tuple;
//import java.util.List;
//
//public interface ArticleRepository extends JpaRepository<Article, String> {
//    List<Article> findAllByBoardId(String boardId);
//
//    @Query("select a.userId, a.title from Article a where a.userId=:userId")
//    List<Tuple> selectDistict(@Param("userId") long userId);
//
//    @Query("select a.userId from Article a where a.userId=:userId")
//    List<Article> selectAll(@Param("userId") long userId);
//}
