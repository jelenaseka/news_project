package com.example.news_project.controllers;

import com.example.news_project.apiservices.interfaces.UserAPIService;
import com.example.news_project.controllers.interfaces.IUserController;
import com.example.news_project.model.*;
import com.example.news_project.validators.RegisterUserValidator;
import com.example.news_project.validators.UserRequestValidator;
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
    @Inject
    private UserRequestValidator userRequestValidator;

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
        return userAPIService.findById(id);
    }

    //TODO set isActive
    @Override
    public UserResponse create(UserRequest userRequest) {
        userRequestValidator.validate(userRequest);
        return userAPIService.create(userRequest);
    }

    @Override
    public void update(UserRequest userRequest, UUID id) {
        userRequestValidator.validate(userRequest);
        userAPIService.update(id, userRequest);
    }

    @Override
    public void delete(UUID id) {
        userAPIService.delete(id);
    }
}
