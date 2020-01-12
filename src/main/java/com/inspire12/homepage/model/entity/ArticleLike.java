package com.inspire12.homepage.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "article_like")
@Entity
@IdClass(ArticleLikePk.class)
@AllArgsConstructor
@NoArgsConstructor
public class ArticleLike {
    @Id
    @Column(name = "post_id")
    Long postId;
    @Id
    @Column(name = "username")
    String username;
}
