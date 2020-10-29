package com.inspire12.homepage.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment {
    @Id
    @Column(name = "id")
    int id;

    @Column(name = "article_id")
    @JsonProperty("article_id")
    Long articleId;

    @JsonProperty("username")
    String username;

    @Column(name = "grpno")
    int grpno;

    @Column(name = "grpord")
    int grpord;

    @Column(name = "depth")
    int depth;

    @Column(name = "content")
    @JsonProperty("content")
    String content;
    @Column(name = "likes")
    int likes = 0;
    @Column(name = "created_at")
    @CreationTimestamp
    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonProperty("updated_at")
    LocalDateTime updatedAt;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private User user = new User();

    public static Comment create(String username, Long articleId, String content) {
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setUsername(username);
        comment.setContent(content);
        return comment;
    }
}
