package com.inspire12.homepage.domain.model;

import com.inspire12.homepage.common.LikeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Version;
import java.time.LocalDateTime;

@Entity
@IdClass(UserLikeId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLike {
    @Id
    Long targetId;
    @Id
    Long userId;

    @Id
    @Enumerated(EnumType.STRING)
    LikeType likeType;

    boolean liked;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Version
    Long version;
}
