//package com.inspire12.homepage.service.user;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import com.sun.deploy.net.HttpResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.web.header.Header;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import sun.net.www.http.HttpClient;
//
//import javax.servlet.http.HttpSession;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class KakaoService {
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    private final static String K_CLIENT_ID = "나의 앱 키 입력";
//    private final static String K_REDIRECT_URI = "리다이렉트 주소입력";
//
//    public String getAuthorizationUrl(HttpSession session) {
//
//        String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?"
//                + "client_id=" + K_CLIENT_ID + "&redirect_uri="
//                + K_REDIRECT_URI + "&response_type=code";
//        return kakaoUrl;
//    }
//
//    public String getAccessToken(String autorize_code) {
//
//        final String requestUrl = "https://kauth.kakao.com/oauth/token";
//
//        restTemplate.postForEntity(requestUrl, )
//
//        postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
//        postParams.add(new BasicNameValuePair("client_id", K_CLIENT_ID)); // REST API KEY
//        postParams.add(new BasicNameValuePair("redirect_uri", K_REDIRECT_URI)); // 리다이렉트 URI
//        postParams.add(new BasicNameValuePair("code", autorize_code)); // 로그인 과정 중 얻은 code 값
//
//        JsonNode returnNode = null;
//
//        try {
//
//            post.setEntity(new UrlEncodedFormEntity(postParams));
//            final HttpResponse response = client.execute(post);
//            final int responseCode = response.getStatusLine().getStatusCode();
//
//            // JSON 형태 반환값 처리
//
//            ObjectMapper mapper = new ObjectMapper();
//            returnNode = mapper.readTree(response.getEntity().getContent());
//
//        } catch (UnsupportedEncodingException e) {
//
//            e.printStackTrace();
//
//        } catch (ClientProtocolException e) {
//
//            e.printStackTrace();
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//        } finally {
//            // clear resources
//        }
//        return returnNode.get("access_token").toString();
//    }
//
//    public JsonNode getKakaoUserInfo(String authorizeCode) {
//
//        final String RequestUrl = "https://kapi.kakao.com/v1/user/me";
//        //String CLIENT_ID = K_CLIENT_ID; // REST API KEY
//        //String REDIRECT_URI = K_REDIRECT_URI; // 리다이렉트 URI
//        //String code = autorize_code; // 로그인 과정중 얻은 토큰 값
//        HttpHeaders header = new HttpHeaders();
//
//        final HttpClient client = HttpClientBuilder.create().build();
//        final HttpPost post = new HttpPost(RequestUrl);
//        String accessToken = getAccessToken(authorizeCode);
//        // add header
//        post.addHeader("Authorization", "Bearer " + accessToken);
//
//        JsonNode returnNode = null;
//
//        try {
//
//            final HttpResponse response = client.execute(post);
//            final int responseCode = response.getStatusLine().getStatusCode();
//            System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
//            System.out.println("Response Code : " + responseCode);
//
//            // JSON 형태 반환값 처리
//            ObjectMapper mapper = new ObjectMapper();
//            returnNode = mapper.readTree(response.getEntity().getContent());
//        } catch (UnsupportedEncodingException e) {
//
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//
//            e.printStackTrace();
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        } finally {
//
//            // clear resources
//        }
//        return returnNode;
//    }
//}
//
//
//
//
//}
