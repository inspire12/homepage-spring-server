package com.inspire12.homepage.domain.repository;

import com.inspire12.homepage.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByArticleId(Long articleId);

    @Query(value = "select * from comment where article_id=:articleId order by no desc, grpord asc", nativeQuery = true)
//    @Query(value = "select c from Comment c where c.articleId= :articleId")
    List<Comment> selectCommentByArticleOrder(@Param("articleId") Long articleId);//, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO comment (article_id, username, content, `no`) VALUES (:article_id, :username, :content, (select last_insert_id()+1))", nativeQuery = true)
    void insertByRequest(@Param("article_id") Long articleId, @Param("username") String userId, @Param("content") String content);

    @Modifying
    @Transactional
    @Query(value = "UPDATE comment set grpord = grpord + 1 where `no` = :no and grpord > :grpord", nativeQuery = true)
    void updateReplyOrder(@Param("no") int no, @Param("grpord") int grpord);

}
