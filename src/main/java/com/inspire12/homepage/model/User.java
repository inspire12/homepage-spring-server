package com.inspire12.homepage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
public class User {


    @Id
    @Column(name = "id")
    @JsonProperty("id")
    int id;

    @Column(name="email")
    String email;

    @Column(name = "nickname")
    @JsonProperty("nickname")
    String nickname;

    @Column(name = "name")
    @JsonProperty("name")
    String name;

    @Column(name = "passwd")
    @JsonProperty("passwd")
    String passwd;

    @Column(name="created_at")
    @CreationTimestamp
    @JsonProperty("created_at")
    LocalDateTime createdAt;
}
