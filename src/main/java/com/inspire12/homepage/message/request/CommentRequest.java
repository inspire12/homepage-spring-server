package com.inspire12.homepage.message.request;

import lombok.Value;

@Value
public class CommentRequest {
    Long articleId;
    String content;
    Long parentId;
}
