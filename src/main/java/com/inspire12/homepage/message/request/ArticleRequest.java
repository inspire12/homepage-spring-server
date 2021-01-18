package com.inspire12.homepage.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inspire12.homepage.aspect.TextContentAspect;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@Getter
@Setter
@Valid
@ToString
public class ArticleRequest {
    @JsonProperty("id")
    Long id;

    @TextContentAspect
    String title;

    String content;

    Integer type;

    Long parentId;
}
