package com.inspire12.homepage.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleLikeId implements Serializable {
    private static final long serialVersionUID = -4144658576676492364L;
    private Long postId;
    private Long userId;
}
