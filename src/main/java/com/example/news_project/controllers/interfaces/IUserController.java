package com.example.news_project.controllers.interfaces;

import com.example.news_project.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;


public interface IUserController extends IEntityController<UserRequest, UserResponse, UserFilterParams> {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    UserResponse register(@RequestBody RegisterUserRequest registerUserRequest);

    @Override
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    Iterable<UserResponse> findAll(@RequestBody UserFilterParams userFilterParams);

    @Override
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    UserResponse findById(@PathVariable UUID id);

    @Override
    @PreAuthorize("hasAnyAuthority('USER_CREATE')")
    UserResponse create(@RequestBody UserRequest userRequest);

    @Override
    @PreAuthorize("hasAnyAuthority('USER_UPDATE')")
    void update(@RequestBody UserRequest userRequest, @PathVariable UUID id);

    @Override
    @PreAuthorize("hasAnyAuthority('USER_DELETE')")
    void delete(@PathVariable UUID id);
}
