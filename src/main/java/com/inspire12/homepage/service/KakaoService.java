package com.inspire12.homepage.service;

import com.inspire12.homepage.model.kakao.KakaoAuth;
import com.inspire12.homepage.model.kakao.KakaoProfile;
import lombok.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoService {
    private RestTemplate restTemplate;
    private Environment env;
//    private Gson gson;
//    @Value("${spring.url.base}")
//    private String baseUrl;
//    @Value("${spring.social.kakao.client_id}")
//    private String kakaoClientId;
//    @Value("${spring.social.kakao.redirect}")
//    private String kakaoRedirect;

    public void getKakaoProfile(String accessToken) {
        // Set header : Content-type: application/x-www-form-urlencoded
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.set("Authorization", "Bearer " + accessToken);
//        // Set http entity
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
//        try {
//            // Request profile
//            ResponseEntity<String> response = restTemplate.postForEntity(env.getProperty("spring.social.kakao.url.profile"), request, String.class);
//            if (response.getStatusCode() == HttpStatus.OK)
//                return gson.fromJson(response.getBody(), KakaoProfile.class);
//        } catch (Exception e) {
//            throw new Exception();
//        }
//        throw new Exception();
//    }
//    public KakaoAuth getKakaoTokenInfo(String code) {
//        // Set header : Content-type: application/x-www-form-urlencoded
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        // Set parameter
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", kakaoClientId);
//        params.add("redirect_uri", baseUrl + kakaoRedirect);
//        params.add("code", code);
//        // Set http entity
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity(env.getProperty("spring.social.kakao.url.token"), request, String.class);
//        if (response.getStatusCode() == HttpStatus.OK) {
//            return gson.fromJson(response.getBody(), RetKakaoAuth.class);
//        }
//        return null;
    }
}
