package com.inspire12.homepage.dto.user;

import com.inspire12.homepage.domain.model.AppUser;
import lombok.Value;

@Value
public class AppUserInfo {
    Long userId;

    public static AppUserInfo create(AppUser user) {
        return new AppUserInfo(user.getId());
    }
}
