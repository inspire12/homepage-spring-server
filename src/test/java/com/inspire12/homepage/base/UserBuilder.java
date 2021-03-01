package com.inspire12.homepage.base;

import com.inspire12.homepage.common.AuthType;
import com.inspire12.homepage.dto.user.AppUserInfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserBuilder {
    public static Map<Long, AppUserInfo> userInfoMap() {
        Map<Long, AppUserInfo> map = new HashMap<>();
        map.put(1L, userInfo());
        return map;
    }

    public static AppUserInfo userInfo() {
        return new AppUserInfo(1L,
                "테스트 계정",
                "닉네임",
                Collections.singletonList(AuthType.USER));
    }
}
