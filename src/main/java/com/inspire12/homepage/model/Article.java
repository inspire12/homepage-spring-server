package com.inspire12.homepage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "article")
@Getter
@Setter
public class Article {

    @Id
    @Column(name = "id")
    int id;

    @Nullable
    @Column(name = "no")
    int no;

    @Nullable
    @Column(name = "depth")
    int depth;

    @Column(name = "subject")
    String subject;

    @Column(name = "content")  // html 으로
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
    int boardId;


    @Column(name = "tags")
    String tags;

    @Column(name = "hit")
    Integer hit;

    @Column(name = "like")
    Integer like;

}

