package com.inspire12.homepage.message.viewmodel;

import lombok.Value;

@Value
public class ArticleSaveResponse implements CommonViewResponse {
    String boardType;
    Long boardId;
}
