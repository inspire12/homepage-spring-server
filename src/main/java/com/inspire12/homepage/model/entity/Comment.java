package com.inspire12.homepage.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inspire12.homepage.message.CommentMsg;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    int articleId;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    int userId;

    @Column(name = "no")
    int grpno;

    @Column(name = "grpord")
    int grpord;

    @Column(name = "depth")
    int depth;

    @Column(name = "content")
    @JsonProperty("content")
    String content;

    int like;


    @Column(name = "created_at")
    @CreationTimestamp
    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonProperty("updated_at")
    LocalDateTime updatedAt;

    public static Comment create() {
        Comment comment = new Comment();

        return comment;
    }
}
