package com.inspire12.homepage.security;

import com.inspire12.homepage.model.entity.User;
import com.inspire12.homepage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.existsById(username)) {
            return userRepository.findById(username).get();
        }
        throw new UsernameNotFoundException("유저를 찾을 수 없습니다.");
    }

    public boolean isExistUser(User user) {
        return userRepository.existsById(user.getUsername());
    }

    public User readUser(String username){
        return userRepository.findById(username).get();
    }
    private static List<GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);

    }

    public void setLastLoginedAt(String username){
        userRepository.updateUserLastLoginTime(username);
    }

    public int setNewPassword(String username, String encryptedPassword) {
        return userRepository.updateNewPassword(username, encryptedPassword);
    }
}
