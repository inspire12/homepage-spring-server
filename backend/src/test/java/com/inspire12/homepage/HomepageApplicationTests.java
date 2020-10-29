package com.inspire12.homepage;

import com.inspire12.homepage.assembler.MsgFactory;
import com.inspire12.homepage.model.entity.ArticleLike;
import com.inspire12.homepage.repository.ArticleLikeRepository;
import com.inspire12.homepage.service.board.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Nested
@DisplayName("should run all assertions even if on fails")
public class HomepageApplicationTests {

    @Autowired
    private ArticleLikeRepository articleLikeRepository;

    @Autowired
    MsgFactory msgFactory;

    @Test
    public void contextLoads() {
    }

    @Test
    public void saveTest() {
        ArticleLike articleLike = new ArticleLike();
        articleLike.setPostId(1L);
        articleLike.setUsername("hi");

        articleLikeRepository.save(articleLike);
        ArticleLikeRepository articleLikeRepository = Mockito.mock(ArticleLikeRepository.class);
        ArticleService articleService = new ArticleService();

    }


}

