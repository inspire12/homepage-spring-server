package com.inspire12.homepage.service;

import com.inspire12.homepage.config.ConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final RestTemplate restTemplate;
    private final Environment env;

    private final ConfigProperties configProperties;

    public void getKakaoProfile(String accessToken) {

    }
}
