package com.inspire12.homepage.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recommend")
@Setter
@Getter
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


    public static Recommend create(int articleId, String username) {
        Recommend recommend = new Recommend();
        recommend.setUsername(username);
        recommend.setArticleId(articleId);
        return recommend;
    }
}
