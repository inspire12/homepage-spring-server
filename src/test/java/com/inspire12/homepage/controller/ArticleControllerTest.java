package com.inspire12.homepage.controller;

import com.inspire12.homepage.base.ArticleBase;
import com.inspire12.homepage.base.UserBuilder;
import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.domain.service.ArticleDomainService;
import com.inspire12.homepage.domain.service.UserDomainService;
import com.inspire12.homepage.message.request.ArticleWriteRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.inspire12.homepage.service.board.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Import(ArticleService.class)
public class ArticleControllerTest extends SpringTestSupport {

    @MockBean
    private ArticleDomainService articleDomainService;
    @MockBean
    private UserDomainService userDomainService;

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("게시글 보기")
    public void showArticleTest() throws Exception {
        int size = 10;
        String url = "/boards?size=" + String.valueOf(size);
        PageRequest pageRequest = PageRequest.of(0, size);
        List<Long> userIds = Arrays.asList(1L);

        given(articleDomainService.getArticleList(pageRequest))
            .willReturn(Arrays.asList(ArticleBase.createDefaultArticle()));

        given(userDomainService.getUserInfoMap(userIds))
                .willReturn(UserBuilder.userInfoMap());

        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void writeArticleTest() throws Exception {
        String url = "/articles/write";
        Long userId = 1L;
        ArticleWriteRequest articleRequest = new ArticleWriteRequest(1, UserBuilder.userInfo(),
                "타이틀", "<html></html>", new ArrayList<>(), new ArrayList<>());
        Article article = Article.of(0, 0,
                articleRequest.getTitle(),
                articleRequest.getContent(),
                userId,
                articleRequest.getBoardId(),
                new ArrayList<>());

        given(articleDomainService.saveArticle(any()))
                .willReturn(ArticleBase.createDefaultArticle());

    }

    @Test
    public void testValid() {

    }
}
