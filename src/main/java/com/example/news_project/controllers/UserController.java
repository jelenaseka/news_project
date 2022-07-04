package com.example.news_project.controllers;

import com.example.news_project.apiservices.interfaces.UserAPIService;
import com.example.news_project.controllers.interfaces.IUserController;
import com.example.news_project.model.*;
import com.example.news_project.order_specifiers.NewsPageableCreator;
import com.example.news_project.order_specifiers.UserPageableCreator;
import com.example.news_project.predicates.NewsPredicateListCreator;
import com.example.news_project.predicates.UserPredicateListCreator;
import com.example.news_project.validators.RegisterUserValidator;
import com.example.news_project.validators.UserFilterParamsValidator;
import com.example.news_project.validators.UserRequestValidator;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;
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
    @Inject
    private UserFilterParamsValidator userFilterParamsValidator;
    @Inject
    private UserPageableCreator userPageableCreator;
    @Inject
    private UserPredicateListCreator userPredicateListCreator;

    @Override
    public UserResponse register(RegisterUserRequest registerUserRequest) {
        registerUserValidator.validate(registerUserRequest);
        return userAPIService.register(registerUserRequest);
    }

    @Override
    public Iterable<UserResponse> findAll(UserFilterParams userFilterParams) {
        userFilterParamsValidator.validate(userFilterParams);
        List<Predicate> predicates = userPredicateListCreator.createPredicates(userFilterParams);
        Pageable pageable = userPageableCreator.createPageable(userFilterParams.getOrderBy(), userFilterParams.getSortOrder(), userFilterParams.getPage());
        return userAPIService.findAllByPredicatePageable(predicates, pageable);
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
