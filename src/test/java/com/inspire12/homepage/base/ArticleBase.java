package com.inspire12.homepage.base;

import com.inspire12.homepage.domain.model.Article;

import java.util.Arrays;

public class ArticleBase {
    public static Article createDefaultArticle() {
        return Article.of(1, 0, "타이틀",
                "<html>컨텐츠</html>",
                1L,
                1,
                Arrays.asList("테그","태그"));
    }
}
