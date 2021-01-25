package com.inspire12.homepage.domain.model;

import com.inspire12.homepage.domain.converter.StringToListConverter;
import com.inspire12.homepage.message.request.ArticleRequest;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Generated(GenerationTime.INSERT)
    @Column(name = "no")
    private Integer grpno;

    private Integer grpord;

    private Integer depth;

    private Boolean isDeleted;

    private String subject;

    private String content;

    private String authorName;

    private Long authorId;

    private Integer boardId;

    @Convert(converter = StringToListConverter.class)
    private List<String> tags;

    Integer hit;

    @CreationTimestamp
    private LocalDateTime modifiedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    public static Article createFromRequest(ArticleRequest requestBody) {
        Article article = new Article();
        article.setId(requestBody.getId());
        article.setContent(requestBody.getContent());

        return article;
    }
}

