package com.inspire12.homepage.domain.repository;


import com.inspire12.homepage.domain.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//TODO query dsl 고려
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByDeletedIsFalse(Pageable pageRequest);
    Page<Article> findByBoardIdAndDeletedIsFalse(int boardType, PageRequest pageRequest);
    List<Article> findByGrpNoAndGrpOrderBefore(int grpNo, int grpOrder);

//
//    // 답글을 추가할 때
//    @Modifying
//    @Transactional
//    @Query(value = "INSERT INTO `article` (`subject`, `content`, `username`, `no`, `grpord`, `depth`) VALUES (:subject, :content, :username, :no, :grpord, :depth)", nativeQuery = true)
//    void saveReplyArticle(@Param("subject") String subject, @Param("content") String content, @Param("username") String username, @Param("no") int no, @Param("grpord") int grpord, @Param("depth") int depth);
//
//    @Modifying
//    @Transactional
//    @Query(value = "update article set grpord = grpord + 1 where `no` = :no and grpord > :grpord", nativeQuery = true)
//    void updateReplyOrder(@Param("no") int no, @Param("grpord") int grpord);
}
//
