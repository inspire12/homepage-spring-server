package com.inspire12.homepage.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.inspire12.homepage.model.entity.Article;
import com.inspire12.homepage.model.entity.Comment;
import com.inspire12.homepage.model.entity.User;
import com.inspire12.homepage.util.ArticleUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ArticleMsg {

    int id;
    int no;
    int depth;
    String category;
    String subject;
    String content;
    String url;

    @JsonProperty("author")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    User author;

    @JsonProperty("comments")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    List<CommentMsg> comments;

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
        articleMsg.setNo(article.getGrpno());
        articleMsg.setCategory(ArticleUtil.getArticleCategory(article.getBoardId()));
        articleMsg.setDepth(article.getDepth());
        articleMsg.setSubject(article.getSubject());
        articleMsg.setContent(article.getContent());
        articleMsg.setCreatedAt((article.getCreatedAt()));
        articleMsg.setUpdatedAt((article.getUpdatedAt()));
        articleMsg.setBoardId(article.getBoardId());
        articleMsg.setTags(Arrays.asList(article.getTags().split(",")));
        articleMsg.setAuthor(user);
        articleMsg.setHit(article.getHit());
        articleMsg.setLike(article.getLike());

        return articleMsg;
    }
    public static ArticleMsg createWithComments(Article article, User user, List<CommentMsg> commentMsgs) {

        ArticleMsg articleMsg = create(article, user);
//        articleMsg.setComments(commentMsgs);
        return articleMsg;
    }
}
