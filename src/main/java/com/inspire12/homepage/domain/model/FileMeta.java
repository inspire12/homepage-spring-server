package com.inspire12.homepage.domain.model;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class FileMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String filename;

    String fileUrl;

    Long articleId;

    String username;

    String fileType;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Version
    Long version;

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
