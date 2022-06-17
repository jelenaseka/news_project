package com.example.news_project.apiservices;

import com.example.news_project.apiservices.interfaces.UserAPIService;
import com.example.news_project.entities.User;
import com.example.news_project.mappers.IUserMapper;
import com.example.news_project.mappers.Mapper;
import com.example.news_project.model.RegisterUserRequest;
import com.example.news_project.model.UserRequest;
import com.example.news_project.model.UserResponse;
import com.example.news_project.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service
public class UserAPIServiceImpl extends EntityApiServiceImpl<User, UserRequest, UserResponse, UserService> implements UserAPIService {

    @Inject
    private UserService userService;
    @Inject
    private IUserMapper userMapper;

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
    protected String getEntityName() {
        return User.ENTITY_NAME;
    }

    @Override
    public UserResponse register(RegisterUserRequest registerUserRequest) {
        User user = userMapper.convertRegisterUserRequestToUser(registerUserRequest);
        user = userService.create(user);
        return userMapper.convertEntityToEntityResponse(user);
    }
}
