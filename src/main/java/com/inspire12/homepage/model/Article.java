package com.inspire12.homepage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
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
@Table(name = "article")
@Setter
@Getter
public class Article {

    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "subject")
    String subject;

    @Column(name = "content")
    String content;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    int userId;

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
    Long boardId;
}

