package com.inspire12.homepage.domain.model;

import com.inspire12.homepage.message.request.ArticleRequest;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Generated(GenerationTime.INSERT)
    @Column(name = "no")//, columnDefinition = "default '0'")
    private Integer grpno;

    private Integer grpord;

    private Integer depth;

    private Boolean isDeleted;

    private String subject;

    private String content;

    private String username;

    private Long userId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Integer boardId;

    private String tags;

    Integer hit;

//    @org.hibernate.annotations.Fetch(FetchMode.SUBSELECT)
//    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
//    @JoinColumn(name = "articleId")
//    List<Recommend> likes;
//
//    @org.hibernate.annotations.Fetch(FetchMode.SUBSELECT)
//    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
//    @JoinColumn(name = "articleId")
//    private List<Comment> comments = new ArrayList<>();
//
//
//    @org.hibernate.annotations.Fetch(FetchMode.SUBSELECT)
//    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
//    @JoinColumn(name = "articleId")
//    private List<FileMeta> fileMetas = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = true, insertable = false, updatable = false)
//    private AppUser user;

    public static Article createFromRequest(ArticleRequest requestBody) {
        Article article = new Article();
        article.setId(requestBody.getId());
        article.setContent(requestBody.getContent());

        return article;
    }
//    public static Article createFromRequest(ObjectNode requestBody) {
//        Article article = new Article();
//        if (requestBody.has("id")) {
//            article.setId(requestBody.get("id").asLong());
//            article.setGrpno(requestBody.get("id").asInt());
//        } else {
//            article.setCreatedAt(LocalDateTime.now());
//            article.setHit(0);
//        }
//        String username = requestBody.get("username").asText();
//        String title = requestBody.get("title").asText();
//        String content = requestBody.get("content").asText();
//        int boardId = 1;
//        if (requestBody.has("type")){
//            boardId = requestBody.get("type").asInt();
//        }
//        article.setUsername(username);
//        article.setSubject(title);
//        article.setContent(content);
////        article.setTags();
//        article.setBoardId(boardId);
//        article.setUpdatedAt(LocalDateTime.now());
//        return article;
//    }
}

