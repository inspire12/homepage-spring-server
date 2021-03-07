package com.inspire12.homepage.message.request;

import lombok.Value;

import java.util.List;

@Value
public class ArticleModifyRequest {
    Long id;
    String boardType;
    String title;
    String content;
    List<String> tag;
    List<Object> files;
}
