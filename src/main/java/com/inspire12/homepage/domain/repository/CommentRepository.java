package com.inspire12.homepage.domain.repository;

import com.inspire12.homepage.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// JPA 변환 생각
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByArticleId(Long articleId, PageRequest pageRequest);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO comment (articleId, username, content, `grpNo`) VALUES (:articleId, :username, :content, (select last_insert_id()+1))", nativeQuery = true)
    void insertByRequest(Long articleId, @Param("username") String userId, @Param("content") String content);

    @Modifying
    @Transactional
    @Query(value = "UPDATE comment set grpOrd = grpord + 1 where `grpNo` = :no and grpOrd > :grpord", nativeQuery = true)
    void updateReplyOrder(@Param("no") int no, @Param("grpord") int grpord);

}
