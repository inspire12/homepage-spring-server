package com.inspire12.homepage.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "article")
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int id;

    @Nullable
    @Column(name = "no")
    int grpno;

    @Nullable
    @Column(name = "grpord")
    int grpord;

    @Nullable
    @Column(name = "depth")
    int depth;

    @Column(name = "subject")
    String subject;

    @Column(name = "content")  // html 으로
    String content;

    @Column(name = "username")
    @JsonProperty("username")
    String username;

    @Column(name = "created_at")
    @CreationTimestamp
    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonProperty("updated_at")
    LocalDateTime updatedAt;

    @Column(name = "board_id")
    @JsonProperty("board_id")
    int boardId;


    @Column(name = "tags")
    String tags;

    @Column(name = "hit")
    Integer hit;

    @Column(name = "like")
    Integer like;

    public static Article createFromRequest(ObjectNode requestBody) {
        Article article = new Article();
        String userId = requestBody.get("user_id").asText();
        String content = requestBody.get("content").asText();
        article.setUsername(userId);
        article.setContent(content);
        return article;
    }

}

