package com.inspire12.homepage.model.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inspire12.homepage.model.entity.Comment;
import com.inspire12.homepage.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentMsg {

    int id;

    @JsonProperty("article_id")
    Long articleId;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    User author;

    int grpno;
    int grpord;
    int depth;

    @JsonProperty("content")
    String content;

    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @JsonProperty("updated_at")
    LocalDateTime updatedAt;

    int like;

    public static CommentMsg create(Comment comment){
        CommentMsg commentMsg = new CommentMsg();
        commentMsg.setId(comment.getId());
        commentMsg.setArticleId(comment.getArticleId());
        commentMsg.setAuthor(comment.getUser());
        commentMsg.setContent(comment.getContent());
        commentMsg.setGrpno(comment.getGrpno());
        commentMsg.setGrpord(comment.getGrpord());
        commentMsg.setDepth(comment.getDepth());
        commentMsg.setLike(comment.getLike());
        commentMsg.setCreatedAt(comment.getCreatedAt());
        commentMsg.setUpdatedAt(comment.getUpdatedAt());
        return commentMsg;
    }

    public static CommentMsg createCommentMsg(Comment comment, User user){
        CommentMsg commentMsg = CommentMsg.create(comment);
        commentMsg.setAuthor(user);
        return commentMsg;
    }
}
