package com.example.news_project.apiservices;

import com.example.news_project.entities.User;
import com.example.news_project.mappers.Mapper;
import com.example.news_project.model.UserRequest;
import com.example.news_project.model.UserResponse;
import com.example.news_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserAPIServiceImpl extends EntityApiServiceImpl<User, UserRequest, UserResponse, UserService> implements UserAPIService {

    @Autowired
    private UserService userService;
    @Autowired
    private Mapper<User, UserRequest, UserResponse> userMapper;

    public UserAPIServiceImpl(UserService userService, Mapper<User, UserRequest, UserResponse> userMapper) {
        super(userService, userMapper);
    }

    @Override
    protected User setFields(User user, UserRequest userRequest) {
        user.setFullName(userRequest.getFullName());
        user.setUsername(userRequest.getUsername());
        return user;
    }

    @Override
    protected void validation(User user) {
    //check if username exists
    }

    @Override
    protected String getEntityName() {
        return "User";
    }


}
