package com.inspire12.homepage.message.request;

import com.inspire12.homepage.dto.user.AppUserInfo;
import lombok.Value;

import java.util.List;

@Value
public class ArticleWriteRequest {
      Integer boardId;
      AppUserInfo user;
      String title;
      String content;
      List<String> tag;
      List<Object> files;
}
