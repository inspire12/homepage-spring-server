package com.inspire12.homepage.message.response;

import com.inspire12.homepage.dto.message.CommentMsg;
import lombok.Value;

import java.util.List;

@Value
public class CommentResponse {
    List<CommentMsg> comments;
}
