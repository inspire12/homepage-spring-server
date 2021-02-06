package com.inspire12.homepage.message.request;

import com.inspire12.homepage.dto.user.AppUserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleWritingRequest {
      Integer type;
      AppUserInfo user;
      String title;
      String content;
      List<String> tag;
      List<Object> files;
}
