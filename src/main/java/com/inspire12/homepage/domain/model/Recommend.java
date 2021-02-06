package com.inspire12.homepage.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
@Data
public class Recommend {
    @Id
    private Integer id;

    private String username;

    private Long targetId;

    @Version
    private Long version;

    public static Recommend create(Long articleId, String username) {
        Recommend recommend = new Recommend();
        recommend.setUsername(username);
        recommend.setTargetId(articleId);
        return recommend;
    }
}
