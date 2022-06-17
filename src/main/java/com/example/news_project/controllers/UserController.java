package com.example.news_project.controllers;

import com.example.news_project.apiservices.NewsAPIService;
import com.example.news_project.apiservices.UserAPIService;
import com.example.news_project.model.*;
import com.example.news_project.services.UserService;
import com.example.news_project.validators.NewsRequestValidator;
import com.example.news_project.validators.RegisterUserValidator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController implements IUserController {
    @Inject
    private UserAPIService userAPIService;
    @Inject
    private RegisterUserValidator registerUserValidator;

    @Override
    public UserResponse register(RegisterUserRequest registerUserRequest) {
        registerUserValidator.validate(registerUserRequest);
        return userAPIService.register(registerUserRequest);
    }

    @Override
    public Iterable<UserResponse> findAll(UserFilterParams userFilterParams) {
        return null;
    }

    @Override
    public UserResponse findById(UUID id) {
        return null;
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        return null;
    }

    @Override
    public void update(UserRequest userRequest, UUID id) {

    }

    @Override
    public void delete(UUID id) {

    }
}
