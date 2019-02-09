package com.inspire12.homepage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    String userId;

    @Column(name = "nick_name")
    @JsonProperty("nick_name")
    String nickName;

    @Column(name = "name")
    @JsonProperty("name")
    String name;

}
