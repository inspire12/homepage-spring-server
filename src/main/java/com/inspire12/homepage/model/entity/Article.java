package com.inspire12.homepage.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.homepage.model.request.ArticleRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Generated(GenerationTime.INSERT)
    @Column(name = "no")
    int grpno;

    @Column(name = "grpord")
    int grpord = 0;

    @Column(name = "depth")
    int depth = 0;

    @Column(name = "is_deleted")
    @JsonProperty(value = "is_deleted")
    Boolean isDeleted = false;

    @Column(name = "subject")
    String subject;

    @Column(name = "content")  // html 으로
    String content;

//    @Transient
    @Column(name="username")
    @JsonProperty("username")
    String username;

    @Column(name = "created_at")
    @CreationTimestamp
    @JsonProperty("created_at")
    LocalDateTime createdAt = LocalDateTime.now();


    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonProperty("updated_at")
    LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "board_id")
    @JsonProperty("board_id")
    int boardId;

    @Column(name = "tags", nullable = true)
    String tags;


    @Column(name = "hit")
    Integer hit = 0;


    @org.hibernate.annotations.Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "article_id")
    List<Recommend> likes;

    @org.hibernate.annotations.Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "article_id")
    private List<Comment> comments = new ArrayList<>();


    @org.hibernate.annotations.Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "article_id")
    private List<FileMeta> fileMetas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = true, insertable = false, updatable = false)
    private User user;

    public static Article createFromRequest(ArticleRequest requestBody) {
        Article article = new Article();
        article.setId(requestBody.getId());
        article.setContent(requestBody.getContent());

        return article;
    }
    public static Article createFromRequest(ObjectNode requestBody) {
        Article article = new Article();
        if (requestBody.has("id")) {
            article.setId(requestBody.get("id").asLong());
            article.setGrpno(requestBody.get("id").asInt());
        } else {
            article.setCreatedAt(LocalDateTime.now());
            article.setHit(0);
        }
        String username = requestBody.get("username").asText();
        String title = requestBody.get("title").asText();
        String content = requestBody.get("content").asText();
        int boardId = 1;
        if (requestBody.has("type")){
            boardId = requestBody.get("type").asInt();
        }
        article.setUsername(username);
        article.setSubject(title);
        article.setContent(content);
//        article.setTags();
        article.setBoardId(boardId);
        article.setUpdatedAt(LocalDateTime.now());
        return article;
    }



}

