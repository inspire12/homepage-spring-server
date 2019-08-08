//package com.inspire12.homepage.service;
//
//import com.inspire12.homepage.model.User;
//import com.inspire12.homepage.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//// Todo : 추후 인증과 관련된 부분 spring-security part 에서 다룰 예정
//@Service
//public class UserService {
////    @Autowired
////    UserRepository userRepository;
////
////    public boolean createUser(User user){
////        userRepository.save(user);
////        return true;
////    }
////
////    public User findUser(String id){
////        User user = userRepository.findByUserId(id);
////        return user;
////    }
////
////    public List<User> listUp(){
////        List<User> users = userRepository.findAll();
////        return users;
////    }
////
////    // TODO : 추후 계정복구 가능하게 백업 디비 설정필요
////    public boolean deleteUser(String userId, String passwd){
////        User user = userRepository.findByUserId(userId);
////
////        if(user.getPasswd().equals(passwd)){
////            userRepository.deleteByUserId(userId);
////            return true;
////        }
////        return false;
////    }
//}
