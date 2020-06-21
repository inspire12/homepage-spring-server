package com.inspire12.homepage.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.inspire12.homepage.aspect.TextContentAspect;
import com.inspire12.homepage.model.Constant;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Getter
@Setter
@Valid
public class ArticleRequest {
    @JsonProperty("id")
    Long id;

//    @Size(max = 10)
    @TextContentAspect(message = Constant.a)
    @JsonProperty("title")
    String title;

    @JsonProperty("content")
    String content;

    @JsonProperty("type")
    Integer type;


}
