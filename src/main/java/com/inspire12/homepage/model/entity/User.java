package com.inspire12.homepage.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Setter
@Getter
public class User  {


    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    @Column(name = "email")
    @JsonProperty("email")
    String email;

    @Column(name = "nickname")
    @JsonProperty("nickname")
    String nickname;

    @Column(name = "name")
    @JsonProperty("name")
    String name;

    @Column(name = "passwd")
    @JsonIgnore
    String passwd;

    @Column(name="created_at")
    @CreationTimestamp
    @JsonProperty("created_at")
    LocalDateTime createdAt;
}
