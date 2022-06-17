package com.example.news_project.controllers.interfaces;

import com.example.news_project.model.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface IUserController extends IEntityController<UserRequest, UserResponse, UserFilterParams> {

    @PostMapping("/register")
    UserResponse register(@RequestBody RegisterUserRequest registerUserRequest);
}
