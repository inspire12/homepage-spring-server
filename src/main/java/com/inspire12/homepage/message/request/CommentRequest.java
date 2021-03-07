package com.inspire12.homepage.message.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Value
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    Long articleId;
    Long commentId;
    String content;
    Long parentId;
}
