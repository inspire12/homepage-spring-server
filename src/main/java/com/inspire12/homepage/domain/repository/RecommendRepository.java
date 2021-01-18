package com.inspire12.homepage.domain.repository;

import com.inspire12.homepage.domain.model.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
}
