package com.inspire12.homepage.domain.repository;

import com.inspire12.homepage.domain.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <AppUser, Long>{
    void deleteByUsername(String username);

    Optional<AppUser> findByUsernameAndPassword(String username, String password);

    Optional<AppUser> findByUsername(String username);
    List<AppUser> findAllByUsernameIn(List<String> username);

    Boolean existsByUsername(String username);
}
