package com.inspire12.homepage.dto.redis;
import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@RedisHash("person")
@Getter
@Setter
@ToString
public class Redis implements Serializable{

    private static final long serialVersionUID = 1370692830319429806L;

    @Id
    private Long id;

    //    @Indexed
    private String firstname;

    //    @Indexed
    private String lastname;

    private int age;

}

