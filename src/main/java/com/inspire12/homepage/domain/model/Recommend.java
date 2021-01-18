package com.inspire12.homepage.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Recommend {
    @Id
    Integer id;

    String username;

    Long articleId;


    public static Recommend create(Long articleId, String username) {
        Recommend recommend = new Recommend();
        recommend.setUsername(username);
        recommend.setArticleId(articleId);
        return recommend;
    }
}
