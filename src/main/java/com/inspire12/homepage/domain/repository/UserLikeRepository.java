package com.inspire12.homepage.domain.repository;

import com.inspire12.homepage.domain.model.UserLike;
import com.inspire12.homepage.domain.model.UserLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikeRepository extends JpaRepository<UserLike, UserLikeId> {
}
