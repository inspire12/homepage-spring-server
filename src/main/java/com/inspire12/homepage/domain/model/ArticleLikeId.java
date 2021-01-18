package com.inspire12.homepage.domain.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleLikeId implements Serializable {
    private static final long serialVersionUID = -4144658576676492364L;
    private Long postId;
    private String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleLikeId)) return false;
        ArticleLikeId o1 = (ArticleLikeId) o;
        return Objects.equals(getPostId(), o1.getPostId()) && Objects.equals(getUsername(), o1.getUsername()) ;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
