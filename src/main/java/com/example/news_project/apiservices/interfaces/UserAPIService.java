package com.example.news_project.apiservices.interfaces;

import com.example.news_project.model.RegisterUserRequest;
import com.example.news_project.model.UserRequest;
import com.example.news_project.model.UserResponse;

public interface UserAPIService extends EntityAPIService<UserRequest, UserResponse> {
    UserResponse register(RegisterUserRequest registerUserRequest);
}
