package com.inspire12.homepage.domain.model;

import com.inspire12.homepage.common.AuthType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

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

    @Enumerated(EnumType.STRING)
    AuthType role;

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
        user.setRole(AuthType.USER);
        return user;
    }

    public static AppUser create(String username, String email, String password, int studentId, String realName) {
        AppUser user = create(username, email, password);
        user.setStudentId(studentId);
        user.setName(realName);
        return user;
    }
}
