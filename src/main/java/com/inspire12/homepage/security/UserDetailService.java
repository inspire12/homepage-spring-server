package com.inspire12.homepage.security;

import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService {//implements UserDetailsService {

    private final UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        return userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
//    }

    public boolean isExistUser(AppUser user) {
        return userRepository.existsByUsername(user.getUsername());
    }

    public Optional<AppUser> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    private static List<GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Transactional
    public void saveUser(AppUser user) {
        userRepository.save(user);
    }

    public void setLastLoginAt(String username){
        userRepository.updateUserLastLoginTime(username);
    }

    public int setNewPassword(String username, String encryptedPassword) {
        return userRepository.updateNewPassword(username, encryptedPassword);
    }
}
