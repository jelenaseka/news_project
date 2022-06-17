package com.example.news_project.services.interfaces;

public interface LoginAttemptService {
    void evictUserFromLoginAttemptCache(String username);
    void addUserToLoginAttemptCache(String username);
    boolean hasExceededMaxAttempts(String username);
}
