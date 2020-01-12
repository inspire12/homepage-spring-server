package com.inspire12.homepage.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="files_meta")
@Getter
@Setter
public class FileMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "filename")
    String filename;

    @Column(name = "file_url")
    @JsonProperty("file_url")
    String fileUrl;

    @Column(name = "article_id")
    @JsonProperty("article_id")
    Long articleId;

    @Column(name = "username")
    String username;

    @Column(name = "file_type")
    @JsonProperty("file_type")
    String fileType;

    @Column(name = "created_at")
    @JsonProperty("created_at")
    @CreationTimestamp
    LocalDateTime createdAt;


    public static FileMeta create(JsonNode file, Article article) {
        FileMeta fileMeta = new FileMeta();
        fileMeta.setArticleId(article.getId());
        fileMeta.setUsername(article.getUsername());
        fileMeta.setFileType(file.get("type").asText());
        fileMeta.setFilename(file.get("filename").asText());
        fileMeta.setFileUrl(file.get("file-url").asText());
        return fileMeta;
    }
}
