package com.inspire12.homepage.controller;

import com.inspire12.homepage.HomepageApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HomepageApplication.class)
@ActiveProfiles("test")
public abstract class SpringTestSupport {
}
