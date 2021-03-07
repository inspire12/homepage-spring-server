package com.inspire12.homepage.message.response;

import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.domain.model.FileMeta;
import com.inspire12.homepage.dto.article.CommentInfo;
import com.inspire12.homepage.dto.user.AppUserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleInfo {

    Long id;
    int no;
    int depth;
    String title;
    String content;
    String url;
    Integer likes;
    AppUserInfo author;
    List<CommentInfo> comments = new ArrayList<>();
    Integer commentsCount;
    List<FileMeta> files = new ArrayList<>();

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String boardType;
    List<String> tags;
    Integer hits;

    boolean myLike;
    boolean deleted;

    public static ArticleInfo create(Article article, AppUserInfo userInfo, boolean isMyLike){
        ArticleInfo articleInfo = create(article, userInfo);
        articleInfo.setMyLike(isMyLike);
        return articleInfo;
    }

    public static ArticleInfo create(Article article, AppUserInfo appUserInfo){
        ArticleInfo articleInfo = new ArticleInfo(
                article.getId(),
                article.getGrpNo(),
                0, // TODO
                article.getTitle(),
                article.getContent(),
                "", // TODO
                article.getLikes(),
                appUserInfo,
                new ArrayList<>(),
                article.getCommentCount(),
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                article.getBoardType(),
                new ArrayList<>(), // tag
                article.getHits(),
                false,
                false
        );
        return articleInfo;
    }

    public static ArticleInfo createWithComments(Article article, AppUserInfo user, List<CommentInfo> commentResponse) {

        ArticleInfo articleInfo = create(article, user);
        articleInfo.setComments(commentResponse);
        return articleInfo;
    }
}
