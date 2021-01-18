package com.inspire12.homepage.service.user;

import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<AppUser> users = new ArrayList<>();
        List<String> names = Arrays.asList("inspire12", "hygoni", "Sinyoung3016", "MoonDD99", "wilook");
        for (String name : names) {
            userRepository.findByUsername(name).ifPresent(users::add);
        }
        Collections.shuffle(users);
        return users;
    }

    public Optional<AppUser> getUser(String username) {
        return userRepository.findByUsername(username);
    }
}

