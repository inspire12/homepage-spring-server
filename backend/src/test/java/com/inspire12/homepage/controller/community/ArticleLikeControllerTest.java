package com.inspire12.homepage.controller.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.config.http.MatcherType.mvc;

@AutoConfigureMockMvc
@SpringBootTest(properties = {"testId=goddaehee2", "testName=갓대희"}
        , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//classes = {TestJpaRestController.class, MemberService.class},
class ArticleLikeControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("testId")
    String testId;

    @Test
    void incArticleLike() {
        assertEquals(testId, "goddaehee2");
    }

    @Test
    void decArticleLike() {
    }
}