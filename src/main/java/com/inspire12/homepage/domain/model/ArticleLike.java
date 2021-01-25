package com.inspire12.homepage.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Version;
import java.time.LocalDateTime;

@Entity
@IdClass(ArticleLikeId.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleLike {
    @Id
    Long postId;
    @Id
    String username;

    Boolean isLike;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Version
    Long version;
}
