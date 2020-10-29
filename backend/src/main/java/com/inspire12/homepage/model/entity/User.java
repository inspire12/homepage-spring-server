package com.inspire12.homepage.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "username")
    String username;

    @Column(name = "email")
    @JsonProperty("email")
    @Email(message = "Please provide a valid Email")
    String email;

    @Column(name = "nickname")
    @JsonProperty("nickname")
    String nickname;

    @Column(name = "realname")
    @JsonProperty("name")
    String name;

    @Column(name = "pwd")
    @JsonIgnore
    @Size(min = 5, message = "Password must have at least 5 characters")
    String password;

    @Column(name = "created_at")
    @CreationTimestamp
    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @Column(name = "last_login_at")
    @UpdateTimestamp
    @JsonProperty("last_login_at")
    LocalDateTime lastLoginAt;

    @Column(name = "user_role")
    String role;

    @Column(name = "user_profile")
    String profile;

    @Column(name = "student_id")
    Integer studentId;

//    List<String> role;


    public static User create(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setNickname(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("USER");
        return user;
    }

    public static User create(String username, String email, String password, int studentId, String realName) {
        User user = create(username, email, password);
        user.setStudentId(studentId);
        user.setName(realName);
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}
