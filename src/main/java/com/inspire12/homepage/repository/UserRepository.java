package com.inspire12.homepage.repository;

import com.inspire12.homepage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository <User, String>{
    void deleteByUsername(String username);
    User findByUsernameAndPassward(String username, String passward);

    @Modifying
    @Query(value = "UPDATE users SET last_logined_at = :last_logined_at where username=:username", nativeQuery = true)
    void updateUserLastLoginTime(@Param("last_logined_at") LocalDateTime localDateTime, @Param("username") String username);
}
