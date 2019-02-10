package com.inspire12.homepage.repository;

import com.inspire12.homepage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, String>{
    User findByUserId(String userId);

    void deleteByUserId(String userId);
}
