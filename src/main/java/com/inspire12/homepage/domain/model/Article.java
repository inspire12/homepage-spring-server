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

    private String title;

    private String content;

    private Long authorId;

    private String boardType;

    @Convert(converter = StringToListConverter.class) // TODO
    private List<String> tags;

    private Integer hits;

    private Integer likes;
    private Integer commentCount;

    private boolean deleted;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Version
    private Long version;

    public static Article of(Integer grpNo, Integer grpOrder,
                             String title, String content, Long authorId, String boardType,
                             List<String> tags) {
        return new Article(null, grpNo, grpOrder, title, content, authorId,
                boardType, tags, 0, 0, 0, false, LocalDateTime.now(), LocalDateTime.now(), 0L);
    }

}

