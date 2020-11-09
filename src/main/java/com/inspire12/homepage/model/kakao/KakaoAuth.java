package com.inspire12.homepage.model.kakao;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class KakaoAuth {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;
}
