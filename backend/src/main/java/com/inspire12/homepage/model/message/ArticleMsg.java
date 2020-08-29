package com.inspire12.homepage.model.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.inspire12.homepage.model.entity.*;
import com.inspire12.homepage.util.ArticleUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ArticleMsg {

    Long id;
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

    @JsonProperty("files")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    List<FileMeta> files = new ArrayList<>();

    @JsonProperty("created_at")
    LocalDateTime createdAt;
    @JsonProperty("updated_at")
    LocalDateTime updatedAt;
    @JsonProperty("board_id")
    int boardId;
    List<String> tags;
    Integer hit;

    List<Recommend> like;

    @JsonProperty("is_my_like")
    Boolean isMyLike;

    @JsonProperty("is_deleted")
    Boolean isDeleted;

    public static ArticleMsg create(Article article, boolean isMyLike) {
        ArticleMsg articleMsg = create(article);
        articleMsg.setIsMyLike(isMyLike);
        return articleMsg;
    }

    public static ArticleMsg create(Article article) {
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
        if (article.getTags() != null && !article.getTags().equals(""))
            articleMsg.setTags(Arrays.asList(article.getTags().split(",")));
        articleMsg.setAuthor(article.getUser());

        articleMsg.setFiles(article.getFileMetas());

        articleMsg.setHit(article.getHit());
        articleMsg.setLike(article.getLikes());
        articleMsg.setIsDeleted(article.getIsDeleted());
        List<CommentMsg> commentMsgs = new ArrayList<>();
        for (Comment comment : article.getComments()) {
            commentMsgs.add(CommentMsg.create(comment));
        }

        articleMsg.setComments(commentMsgs);
        return articleMsg;
    }

    public static ArticleMsg createWithUser(Article article, User user) {
        ArticleMsg articleMsg = ArticleMsg.create(article);
        articleMsg.setAuthor(user);
        return articleMsg;
    }

    public static ArticleMsg createWithComments(Article article, User user, List<CommentMsg> commentMsgs) {

        ArticleMsg articleMsg = createWithUser(article, user);
        articleMsg.setComments(commentMsgs);
        return articleMsg;
    }
}
