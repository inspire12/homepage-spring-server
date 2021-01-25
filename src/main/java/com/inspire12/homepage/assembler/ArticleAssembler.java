package com.inspire12.homepage.assembler;

import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.dto.message.ArticleMsg;

import java.util.ArrayList;
import java.util.List;

public class ArticleAssembler {
    public static List<ArticleMsg> convertArticles(List<Article> articles) {
        List<ArticleMsg> articleMsgs = new ArrayList<>();
        for (Article article : articles) {
            try {
                articleMsgs.add(ArticleMsg.create(article));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return articleMsgs;
    }
}
