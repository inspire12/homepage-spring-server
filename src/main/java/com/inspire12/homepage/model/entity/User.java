package com.inspire12.homepage.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="username")
    String username;

    @Column(name = "email")
    @JsonProperty("email")
    @Email(message="Please provide a valid Email")
    String email;

    @Column(name = "nickname")
    @JsonProperty("nickname")
    String nickname;

    @Column(name = "name")
    @JsonProperty("name")
    String name;

    @Column(name = "password")
    @JsonIgnore
    @Length(min=5, message = "Password must have at least 5 characters")
    String password;

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

    @Column(name="profile")
    String profile;

    @Column(name = "student_id")
    int studentId;

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
