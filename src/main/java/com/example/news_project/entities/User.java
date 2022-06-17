package com.example.news_project.entities;

import com.example.news_project.enums.Authority;
import com.example.news_project.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name=User.TABLE_NAME)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User extends AbstractEntity {
    public static final String ENTITY_NAME = "USER";
    public static final String TABLE_NAME = "users";

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String fullName;
    private boolean isNotLocked;
    private boolean isActive;
    private Role role;

    public User(UUID id, boolean isDeleted, LocalDateTime createdAt, LocalDateTime modifiedAt, String username, String password, String fullName, boolean isNotLocked, boolean isActive, Role role) {
        super(id, isDeleted, createdAt, modifiedAt);
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.isNotLocked = isNotLocked;
        this.isActive = isActive;
        this.role = role;
//        this.authorities = authorities;
    }
}
