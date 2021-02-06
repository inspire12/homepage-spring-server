package com.inspire12.homepage.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum ArticleType {

    잡담(0),
    정보(1),
    스터디(2),
    알고리즘(3),
    AI(4),
    개발지식(5) ;

    @Getter
    private final int id;
    private static final Map<String, ArticleType> articleTypeMap = new HashMap<>();

    static {
        for (ArticleType articleType: ArticleType.values()) {
            articleTypeMap.put(articleType.name(), articleType);
        }
    }

    public static ArticleType toArticleType(String type) {
        return articleTypeMap.get(type);
    }
}
