package com.inspire12.homepage.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recommend")
public class Recommend {
    @Id
    @JsonProperty("id")
    @Column(name = "id")
    Integer id;

    @Column(name = "username")
    @JsonProperty("username")
    String username;

    @Column(name = "article_id")
    @JsonProperty("article_id")
    int articleId;

    @Column(name = "comment_id")
    @JsonProperty("comment_id")
    Integer commentId;

}
