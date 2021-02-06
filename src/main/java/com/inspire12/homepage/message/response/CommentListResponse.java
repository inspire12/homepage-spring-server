package com.inspire12.homepage.message.response;

import com.inspire12.homepage.dto.article.CommentInfo;
import lombok.Value;

import java.util.List;

@Value
public class CommentListResponse {
    List<CommentInfo> comments;
}
