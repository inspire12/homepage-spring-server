package com.inspire12.homepage.message.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.model.Article;
import com.inspire12.homepage.domain.model.FileMeta;
import com.inspire12.homepage.dto.article.CommentInfo;
import com.inspire12.homepage.dto.article.RecommendInfo;
import com.inspire12.homepage.dto.user.AppUserInfo;
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
@ToString
public class ArticleInfo {

    Long id;
    int no;
    int depth;
    String category;
    String subject;
    String content;
    String url;

    AppUserInfo author;
    List<CommentInfo> comments;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    List<FileMeta> files = new ArrayList<>();

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String boardType;
    List<String> tags;
    Integer hit;

    List<RecommendInfo> like;

    boolean myLike;
    boolean deleted;

    public static ArticleInfo create(Article article, boolean isMyLike){
        ArticleInfo articleInfo = create(article);
        articleInfo.setMyLike(isMyLike);
        return articleInfo;
    }

    public static ArticleInfo create(Article article){
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setId(article.getId());
        articleInfo.setNo(article.getGrpNo());
        articleInfo.setCategory(article.getBoardType());
        articleInfo.setSubject(article.getTitle());
        articleInfo.setContent(article.getContent());
        articleInfo.setCreatedAt((article.getCreatedAt()));
        articleInfo.setUpdatedAt((article.getUpdatedAt()));
        articleInfo.setBoardType(article.getBoardType());
        articleInfo.setTags(article.getTags());

        articleInfo.setHit(article.getHits());
        articleInfo.setDeleted(article.isDeleted());
        List<CommentInfo> commentResponse = new ArrayList<>();
        articleInfo.setComments(commentResponse);
        articleInfo.setLike(new ArrayList<>());
        return articleInfo;
    }

    public static ArticleInfo createWithUser(Article article, AppUserInfo user) {
        ArticleInfo articleInfo = ArticleInfo.create(article);
        articleInfo.setAuthor(user);
        return articleInfo;
    }

    public static ArticleInfo createWithComments(Article article, AppUser user, List<CommentInfo> commentRespons) {

        ArticleInfo articleInfo = createWithUser(article, AppUserInfo.create(user));
        articleInfo.setComments(commentRespons);
        return articleInfo;
    }
}
