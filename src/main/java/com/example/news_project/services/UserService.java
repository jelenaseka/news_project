package com.example.news_project.services;

import com.example.news_project.entities.User;

import java.util.Optional;

public interface UserService extends GenericService<User> {
    Optional<User> findByUsername(String username);
}
