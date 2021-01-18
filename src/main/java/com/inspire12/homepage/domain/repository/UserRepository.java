package com.inspire12.homepage.domain.repository;

import com.inspire12.homepage.domain.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository <AppUser, Long>{
    void deleteByUsername(String username);
    Optional<AppUser> findByUsernameAndPassword(String username, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET last_logined_at = NOW() where username=:username", nativeQuery = true)
    void updateUserLastLoginTime(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET password=:password where username=:username", nativeQuery = true)
    int updateNewPassword(@Param("username") String username, @Param("password")String password);

    Optional<AppUser> findByUsername(String username);

    Boolean existsByUsername(String username);
}
