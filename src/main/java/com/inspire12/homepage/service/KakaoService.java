package com.inspire12.homepage.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoService {
    private RestTemplate restTemplate;
    private Environment env;
}
