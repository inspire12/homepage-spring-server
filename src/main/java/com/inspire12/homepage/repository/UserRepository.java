package com.inspire12.homepage.repository;

import com.inspire12.homepage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository <User, String>{
    void deleteByUsername(String username);
    User findByUsernameAndPassword(String username, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET last_logined_at = NOW() where username=:username", nativeQuery = true)
    void updateUserLastLoginTime(@Param("username") String username);
}
