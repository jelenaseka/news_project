package com.example.news_project.repositories;

import com.example.news_project.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends GenericRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
