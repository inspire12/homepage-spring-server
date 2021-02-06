package com.inspire12.homepage.domain.model;

import com.inspire12.homepage.domain.converter.StringToListConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer grpNo;

    private Integer grpOrder;

    private Integer depth;

    private String title;

    private String content;

    private Long authorId;

    private Integer boardId;

    @Convert(converter = StringToListConverter.class)
    private List<String> tags;

    private Integer hits;

    private Integer likes;

    private boolean deleted;

    @CreationTimestamp
    private LocalDateTime modifiedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Version
    private Long version;

}

