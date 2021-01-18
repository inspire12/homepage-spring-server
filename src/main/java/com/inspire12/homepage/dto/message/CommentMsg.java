package com.inspire12.homepage.dto.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inspire12.homepage.domain.model.Comment;
import com.inspire12.homepage.domain.model.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentMsg {

    Long id;

    @JsonProperty("article_id")
    Long articleId;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    AppUser author;

    Integer grpno;
    Integer grpord;
    Integer depth;

    @JsonProperty("content")
    String content;

    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @JsonProperty("updated_at")
    LocalDateTime updatedAt;

    Integer likeCount;

    public static CommentMsg create(Comment comment){
        CommentMsg commentMsg = new CommentMsg();
        commentMsg.setId(comment.getId());
        commentMsg.setArticleId(comment.getArticleId());
//        commentMsg.setAuthor(comment.getUser());
        commentMsg.setContent(comment.getContent());
        commentMsg.setGrpno(comment.getGrpno());
        commentMsg.setGrpord(comment.getGrpord());
        commentMsg.setDepth(comment.getDepth());
        commentMsg.setLikeCount(comment.getLikeCount());
        commentMsg.setCreatedAt(comment.getCreatedAt());
        commentMsg.setUpdatedAt(comment.getUpdatedAt());
        return commentMsg;
    }

    public static CommentMsg createCommentMsg(Comment comment, AppUser user){
        CommentMsg commentMsg = CommentMsg.create(comment);
        commentMsg.setAuthor(user);
        return commentMsg;
    }
}
