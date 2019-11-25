package com.inspire12.homepage.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inspire12.homepage.model.entity.Article;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleDto {

    int id;
    int no;
    int depth;
    String subject;
    String content;

    @JsonProperty("user_id")
    int userId;
    String userNickName;

    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @JsonProperty("updated_at")
    LocalDateTime updatedAt;

    @JsonProperty("board_id")
    int boardId;

    public static ArticleDto create(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setNo(article.getGrpno());
        articleDto.setDepth(article.getDepth());
        articleDto.setSubject(article.getSubject());
        articleDto.setUserId(article.getUserId());
//        articleDto.setUserNickName(article.getSubject());
        article.setCreatedAt(article.getCreatedAt());
        article.setUpdatedAt(article.getUpdatedAt());
        article.setBoardId(article.getBoardId());
        return articleDto;
    }
}
