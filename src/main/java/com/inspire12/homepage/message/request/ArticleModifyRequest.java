package com.inspire12.homepage.message.request;

import com.inspire12.homepage.aspect.TextContentAspect;
import lombok.Value;

@Value
public class ArticleModifyRequest {
    Long id;

    @TextContentAspect
    String title;

    String content;

    String boardType;

    Long parentId;
}
