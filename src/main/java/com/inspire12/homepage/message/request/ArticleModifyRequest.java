package com.inspire12.homepage.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inspire12.homepage.aspect.TextContentAspect;
import lombok.Value;

import javax.validation.Valid;

@Valid
@Value
public class ArticleModifyRequest {
    @JsonProperty("id")
    Long id;

    @TextContentAspect
    String title;

    String content;

    Integer boardId;

    Long parentId;
}
