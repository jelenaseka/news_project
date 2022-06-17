package com.example.news_project.mappers;

import com.example.news_project.entities.User;
import com.example.news_project.model.RegisterUserRequest;
import com.example.news_project.model.UserRequest;
import com.example.news_project.model.UserResponse;

public interface IUserMapper extends Mapper<User, UserRequest, UserResponse> {
    User convertRegisterUserRequestToUser(RegisterUserRequest registerUserRequest);
}
