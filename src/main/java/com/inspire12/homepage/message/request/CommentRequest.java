package com.inspire12.homepage.message.request;

import lombok.Value;

@Value
public class CommentRequest {
    private String username;
    private Long articleId;
    private String content;
    private Long parentId;
}
