package com.inspire12.homepage.repository;

import com.inspire12.homepage.model.entity.ArticleLike;
import com.inspire12.homepage.model.entity.ArticleLikePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, ArticleLikePk> {
}
