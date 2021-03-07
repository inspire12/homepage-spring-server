package com.inspire12.homepage.dto.user;

import com.inspire12.homepage.common.AuthType;
import com.inspire12.homepage.domain.model.AppUser;
import lombok.Value;

@Value
public class AppUserInfo {
    Long userId;
    String username;
    String nickname;
    AuthType role;
    String profileImg;

    public static AppUserInfo create(AppUser user) {
        return new AppUserInfo(user.getId(),
                user.getUsername(),
                user.getNickname(),
                user.getRole() != null ? user.getRole() : null,
                user.getProfile());
    }
}
