package com.inspire12.homepage.domain.elastic;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.time.LocalDate;

@Document(indexName = "userInfo")
public class UserInfo {
    @Id
    Long id;

    @Field(type = FieldType.Text)
    String name;

    @Field(type = FieldType.Text)
    String info;

    @Field(type = FieldType.Date, format = DateFormat.date)
    LocalDate registerDate;
}
