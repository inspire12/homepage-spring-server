package com.inspire12.homepage.repository;

import com.inspire12.homepage.model.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, Integer> {

}
