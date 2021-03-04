package com.inspire12.homepage.service.user;

import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.service.UserDomainService;
import com.inspire12.homepage.dto.user.AppUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDomainService userDomainService;

    public List<AppUser> getAdminUsers() {
        List<String> names = Arrays.asList("inspire12", "hygoni", "Sinyoung3016", "MoonDD99", "wilook");
        List<AppUser> users = userDomainService.findAllByUsernameIn(names);
        Collections.shuffle(users);
        return users;
    }

    public Map<Long, AppUserInfo> getUserInfoMap(List<Long> userIds) {
        return userDomainService.findAllById(userIds).stream()
                .collect(Collectors.toMap(AppUser::getId, AppUserInfo::create));
    }

    public Optional<AppUser> getUserByName(String username) {
        return userDomainService.findByUsername(username);
    }

    public Optional<AppUser> getUserById(Long userId) {
        return userDomainService.findById(userId);
    }

    public void saveUser(AppUser appUser) {
        userDomainService.saveUser(appUser);
    }

    public AppUserInfo getSecedeUser() {
        return new AppUserInfo(0L, "탈퇴한 유저", "탈퇴한 유저", null);
    }

    public boolean isExistUser(AppUser user) {
        return userDomainService.isExistUser(user);
    }

    public void setNewPassword(String username, String encryptedPassword) {
        userDomainService.setNewPassword(username, encryptedPassword);
    }
}
