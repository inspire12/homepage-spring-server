package com.inspire12.homepage.dto.user;

import com.inspire12.homepage.domain.model.AppUser;
import lombok.Value;

import java.util.List;

@Value
public class AppUserInfo {
    Long userId;
    String username;
    String nickname;

    List<String> role;

    public static AppUserInfo create(AppUser user) {
        return new AppUserInfo(user.getId(),
                user.getUsername(),
                user.getNickname(),
                user.getRole() != null ? user.getRole() : null);
    }
}
