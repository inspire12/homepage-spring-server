//package com.inspire12.homepage.model;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Data;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.GeneratorType;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "user")
//@Data
//public class User {
//
//
//    @Column(name="id")
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    Integer id;
//
//    @Id
//    @Column(name = "user_id")
//    @JsonProperty("user_id")
//    String userId;
//
//    @Column(name = "nick_name")
//    @JsonProperty("nick_name")
//    String nickName;
//
//    @Column(name = "name")
//    @JsonProperty("name")
//    String name;
//
//    @Column(name = "pwd")
//    @JsonProperty("passwd")
//    String passwd;
//
//    @Column(name="sign_at")
//    @CreationTimestamp
//    @JsonProperty("sign_at")
//    LocalDateTime signAt;
//}
