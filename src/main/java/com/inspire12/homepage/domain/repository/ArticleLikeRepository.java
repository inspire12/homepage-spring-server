package com.inspire12.homepage.domain.repository;

import com.inspire12.homepage.domain.model.ArticleLike;
import com.inspire12.homepage.domain.model.ArticleLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, ArticleLikeId> {
}
