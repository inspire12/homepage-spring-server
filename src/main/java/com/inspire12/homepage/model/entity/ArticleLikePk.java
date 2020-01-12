package com.inspire12.homepage.model.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleLikePk implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    private Long postId;
    private String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleLikePk)) return false;
        ArticleLikePk o1 = (ArticleLikePk) o;
        return Objects.equals(getPostId(), o1.getPostId()) && Objects.equals(getUsername(), o1.getUsername()) ;
    }

}
