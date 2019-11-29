package com.inspire12.homepage.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User  {

    @Id
    @Column(name="username")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    String username;

    @Column(name = "email")
    @JsonProperty("email")
    String email;

    @Column(name = "nickname")
    @JsonProperty("nickname")
    String nickname;

    @Column(name = "name")
    @JsonProperty("name")
    String name;

    @Column(name = "passward")
    @JsonIgnore
    String passward;

    @Column(name="created_at")
    @CreationTimestamp
    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @Column(name="last_logined_at")
    @UpdateTimestamp
    @JsonProperty("last_logined_at")
    LocalDateTime lastLoginedAt;

    @Column(name="role")
    String role;

}
