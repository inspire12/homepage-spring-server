package com.inspire12.homepage.domain.model;

import com.inspire12.homepage.domain.converter.StringToListConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String username;

    @Email(message = "Please provide a valid Email")
    String email;

    String nickname;

    String name;

    @Length(min = 5, message = "Password must have at least 5 characters")
    String password;

    @Convert(converter = StringToListConverter.class)
    List<String> role;

    String profile;

    Integer studentId;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime lastAccessAt;

    @Version
    Long version;

    public static AppUser create(String username, String email, String password) {
        AppUser user = new AppUser();
        user.setId(null);
        user.setUsername(username);
        user.setNickname(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Collections.singletonList("USER"));
        return user;
    }

    public static AppUser create(String username, String email, String password, int studentId, String realName) {
        AppUser user = create(username, email, password);
        user.setStudentId(studentId);
        user.setName(realName);
        return user;
    }
}
