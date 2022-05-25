package com.example.news_project.model;

import com.example.news_project.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends AbstractResponse {
    private String username;
    private String fullName;
    private Role role;

    public UserResponse(UUID id, LocalDateTime createdAt, LocalDateTime modifiedAt, String username, String fullName, Role role) {
        super(id, createdAt, modifiedAt);
        this.username = username;
        this.fullName = fullName;
        this.role = role;
    }
}
