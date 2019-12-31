package com.inspire12.homepage.service.user;

import com.inspire12.homepage.model.entity.User;
import com.inspire12.homepage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String username){
        return userRepository.findById(username).get();
    }
}
