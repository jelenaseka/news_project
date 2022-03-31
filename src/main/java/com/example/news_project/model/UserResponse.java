package com.example.news_project.model;

import com.example.news_project.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends AbstractResponse {
    private String username;
    private String fullName;
    private Role role;
}
