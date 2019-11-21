package com.inspire12.homepage.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inspire12.homepage.model.Article;
import com.inspire12.homepage.model.User;
import com.inspire12.homepage.util.ArticleUtil;
import com.inspire12.homepage.util.TimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ArticleMsg {

    int id;
    int no;
    int depth;

    String type;
    String subject;

    String content;

    String url;

    @JsonProperty("author")
    User author;

    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @JsonProperty("updated_at")
    LocalDateTime updatedAt;

    @JsonProperty("board_id")
    int boardId;

    List<String> tags;

    Integer hit;

    Integer like;


    public static ArticleMsg create(Article article, User user) {
        ArticleMsg articleMsg = new ArticleMsg();
        articleMsg.setId(article.getId());
        articleMsg.setNo(article.getNo());
        articleMsg.setType(ArticleUtil.getArticleType(article.getBoardId()));
        articleMsg.setDepth(article.getDepth());
        articleMsg.setSubject(article.getSubject());
        articleMsg.setContent(article.getContent());
        articleMsg.setCreatedAt((article.getCreatedAt()));
        articleMsg.setUpdatedAt((article.getUpdatedAt()));
        articleMsg.setBoardId(article.getBoardId());
//        articleMsg.setTags();
        articleMsg.setAuthor(user);
        articleMsg.setHit(article.getHit());
        articleMsg.setLike(article.getLike());

        return articleMsg;
    }
}
