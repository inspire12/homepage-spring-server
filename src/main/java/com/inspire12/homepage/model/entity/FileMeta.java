package com.inspire12.homepage.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="files_meta")
@Getter
@Setter
public class FileMeta {
    @Id
    @Column(name = "id")
    int id;

    @Column(name = "filename")
    String filename;

    @Column(name = "file_url")
    @JsonProperty("file_url")
    String fileUrl;

    @Column(name = "article_id")
    @JsonProperty("article_id")
    int articleId;

    @Column(name = "username")
    String username;

    @Column(name = "file_type")
    @JsonProperty("file_type")
    String fileType;

    @Column(name = "created_at")
    @JsonProperty("created_at")
    LocalDateTime createdAt;



}
