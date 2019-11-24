package com.inspire12.homepage.repository;

import com.inspire12.homepage.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByArticleId(int article);
}
