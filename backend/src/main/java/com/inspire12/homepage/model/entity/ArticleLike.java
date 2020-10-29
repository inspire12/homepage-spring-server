package com.inspire12.homepage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "article_likes")
@Entity
@IdClass(ArticleLikePk.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleLike {
    @Id
    @Column(name = "article_id")
    Long postId;
    @Id
    @Column(name = "username")
    String username;
}
