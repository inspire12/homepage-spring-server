package com.inspire12.homepage.service;

import com.inspire12.homepage.model.User;
import com.inspire12.homepage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUser(String id){
        User user = userRepository.findByUserId(id);

        return user;
    }
}
