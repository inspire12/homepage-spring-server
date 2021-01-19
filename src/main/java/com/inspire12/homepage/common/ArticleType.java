package com.inspire12.homepage.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ArticleType {

    잡담(1),
    정보(2),
    스터디(3),
    알고리즘(4),
    AI(5),
    개발지식(6) ;

    private final int id;
}
