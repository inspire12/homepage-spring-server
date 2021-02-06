package com.inspire12.homepage.domain.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long articleId;

    private String username;
    private Integer grpNo;
    private Integer grpOrd;
    private Integer depth;

    private String content;
    private Integer likes;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    public static Comment create(String username, Long articleId, String content) {
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setUsername(username);
        comment.setContent(content);
        return comment;
    }
}
