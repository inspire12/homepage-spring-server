package com.inspire12.homepage.domain.service;

import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.repository.UserRepository;
import com.inspire12.homepage.dto.user.AppUserInfo;
import com.inspire12.homepage.exception.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDomainService {
    private final UserRepository userRepository;

    public Map<Long, AppUserInfo> getUserInfoMap(List<Long> userIds) {
        return userRepository.findAllById(userIds).stream()
                .collect(Collectors.toMap(AppUser::getId, AppUserInfo::create));
    }

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

    public boolean setNewPassword(String username, String encryptedPassword) {
        AppUser appUser = userRepository.findByUsername(username).orElseThrow(CommonException::new);
        appUser.setPassword(encryptedPassword);
        return true;
    }

    public AppUserInfo getSecedeUser() {
        return new AppUserInfo(0L, "탈퇴한 유저", "탈퇴한 유저", new ArrayList<>());
    }
}
