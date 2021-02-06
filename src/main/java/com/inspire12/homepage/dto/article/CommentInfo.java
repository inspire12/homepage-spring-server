package com.inspire12.homepage.dto.article;

import com.inspire12.homepage.domain.model.Comment;
import com.inspire12.homepage.dto.user.AppUserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentInfo {

    Long id;

    Long articleId;

    AppUserInfo author;

    Integer grpNo;
    Integer grpOrder;
    Integer depth;

    String content;
    LocalDateTime createdAt;

    LocalDateTime updatedAt;
    Integer likeCount;

    public static CommentInfo create(Comment comment, AppUserInfo author) {
        CommentInfo commentInfo = new CommentInfo();
        commentInfo.setId(comment.getId());
        commentInfo.setArticleId(comment.getArticleId());
        commentInfo.setAuthor(author);
        commentInfo.setContent(comment.getContent());
        commentInfo.setGrpNo(comment.getGrpNo());
        commentInfo.setGrpOrder(comment.getGrpOrd());
        commentInfo.setDepth(comment.getDepth());
        commentInfo.setLikeCount(comment.getLikes());
        commentInfo.setCreatedAt(comment.getCreatedAt());
        commentInfo.setUpdatedAt(comment.getUpdatedAt());
        return commentInfo;
    }
}
