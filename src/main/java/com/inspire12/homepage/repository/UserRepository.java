package com.inspire12.homepage.repository;

import com.inspire12.homepage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Integer>{
    void deleteById(String userId);
}
