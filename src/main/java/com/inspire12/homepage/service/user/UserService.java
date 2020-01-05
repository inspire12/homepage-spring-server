package com.inspire12.homepage.service.user;

import com.inspire12.homepage.model.entity.Article;
import com.inspire12.homepage.model.entity.User;
import com.inspire12.homepage.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> getAdminUsers() {
        List<User> users = new ArrayList<>();
        List<String> names = Arrays.asList("inspire12", "hygoni", "Sinyoung3016", "MoonDD99", "wilook");
        for (String name : names) {
            try {
                users.add(userRepository.findById(name).get());
            } catch (Exception e) {
                logger.warn("user not found: " + name + e.toString());
            }
        }
        Collections.shuffle(users);
        return users;
    }

    public User getUser(String username){
        return userRepository.findById(username).get();
    }

    public User modifyUser(String username, User modifiedUser) {

        return userRepository.save(modifiedUser);
    }
}
