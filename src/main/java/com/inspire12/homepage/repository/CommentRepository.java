package com.inspire12.homepage.repository;

import com.inspire12.homepage.model.entity.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByArticleId(int article);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO comment (article_id, user_id, content) VALUES (:article_id, :user_id, :content)", nativeQuery = true)
    void insertByRequest(@Param("article_id") int articleId, @Param("user_id")int userId, @Param("content") String content);
}
