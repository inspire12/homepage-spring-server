package com.inspire12.homepage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspire12.homepage.HomepageApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HomepageApplication.class)
@ActiveProfiles("test")
public abstract class SpringTestSupport {
    @Autowired
    ObjectMapper objectMapper;
}
