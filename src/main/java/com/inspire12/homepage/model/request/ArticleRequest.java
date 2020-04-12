package com.inspire12.homepage.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequest {
    @JsonProperty("id")
    Long id;

    @JsonProperty("title")
    String title;

    @JsonProperty("content")
    String content;

    @JsonProperty("type")
    Integer type;

    @JsonProperty("files")
    ArrayNode files;

}
