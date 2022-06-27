package com.example.news_project.model;

import com.example.news_project.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String password; //TODO change to send pwd to email
    private String fullName;
    private List<Authority> authorities;
}
