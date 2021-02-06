package com.inspire12.homepage.domain.model;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class FileMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String filename;

    String fileUrl;

    Long articleId;

    Long userId;

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
        fileMeta.setUserId(article.getAuthorId());
        fileMeta.setFileType(file.get("type").asText());
        fileMeta.setFilename(file.get("filename").asText());
        fileMeta.setFileUrl(file.get("file-url").asText());
        return fileMeta;
    }
}
