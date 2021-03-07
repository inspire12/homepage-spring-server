package com.inspire12.homepage.message.request;

import lombok.Value;

@Value
public class CommentRequest {
    Long articleId;
    Long commentId;
    String content;
    Long parentId;
}
