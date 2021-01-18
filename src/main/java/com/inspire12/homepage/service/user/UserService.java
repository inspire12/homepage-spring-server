package com.inspire12.homepage.service.user;

import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<AppUser> getAdminUsers() {
        List<String> names = Arrays.asList("inspire12", "hygoni", "Sinyoung3016", "MoonDD99", "wilook");
        List<AppUser> users = userRepository.findAllByUsernameIn(names);
        Collections.shuffle(users);
        return users;
    }

    public Optional<AppUser> getUser(String username) {
        return userRepository.findByUsername(username);
    }
}

