package com.inspire12.homepage.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Nullable
    @Column(name = "no")
    int grpno = 1;

    @Nullable
    @Column(name = "grpord")
    int grpord = 0;

    @Nullable
    @Column(name = "depth")
    int depth = 0;

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
    String tags = "";

    @Column(name = "hit")
    Integer hit = 0;

    @Column(name = "like")
    Integer like = 0;

    public static Article createFromRequest(ObjectNode requestBody) {
        Article article = new Article();
        String userId = requestBody.get("user_id").asText();
        String title = requestBody.get("title").asText();
        String content = requestBody.get("content").asText();
        article.setUsername(userId);
        article.setSubject(title);
        article.setContent(content);
        article.setTags("test");
        article.setBoardId(1);
        article.setUpdatedAt(LocalDateTime.now());
        article.setCreatedAt(LocalDateTime.now());
        article.setHit(0);
        article.setLike(0);
        return article;
    }

}

