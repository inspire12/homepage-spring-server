package com.inspire12.homepage.controller;

import com.inspire12.homepage.controller.community.ArticleController;
import com.inspire12.homepage.service.board.ArticleService;
import com.inspire12.homepage.service.board.FileMetaService;
import com.inspire12.homepage.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ArticleControllerTest extends SpringTestSupport{

    @Mock
    ArticleController articleController;
    @InjectMocks
    ArticleService articleService;
    @InjectMocks
    UserService userService;
    @InjectMocks
    FileMetaService fileMetaService;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void testArticleController () throws Exception {
        String url = "/boards";
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()));

    }
}
