package com.example.news_project.entities;

import com.example.news_project.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private Role role;

    public User(UUID id, boolean isDeleted, LocalDateTime createdAt, LocalDateTime modifiedAt, String username, String password, String fullName, Role role) {
        super(id, isDeleted, createdAt, modifiedAt);
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }
}
