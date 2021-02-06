package com.inspire12.homepage.assembler;

import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.dto.user.AppUserInfo;
import com.inspire12.homepage.message.response.ArticleInfo;

public class ArticleAssembler {
    public static ArticleInfo toInfo(Article article, AppUserInfo user) {
        return ArticleInfo.createWithUser(article, user);
    }
}
