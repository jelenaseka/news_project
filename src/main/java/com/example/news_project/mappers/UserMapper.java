package com.example.news_project.mappers;

import com.example.news_project.entities.User;
import com.example.news_project.enums.Role;
import com.example.news_project.model.RegisterUserRequest;
import com.example.news_project.model.UserRequest;
import com.example.news_project.model.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserMapper implements IUserMapper {
    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    public User convertEntityRequestToEntity(UserRequest userRequest) {
        return new User(
                UUID.randomUUID(),
                false,
                LocalDateTime.now(),
                null,
                userRequest.getUsername(),
                userRequest.getPassword(),
                userRequest.getFullName(),
                userRequest.getRole()
        );
    }

    @Override
    public UserResponse convertEntityToEntityResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getCreatedAt(),
                user.getModifiedAt(),
                user.getUsername(),
                user.getFullName(),
                user.getRole()
        );
    }

    @Override
    public User convertRegisterUserRequestToUser(RegisterUserRequest registerUserRequest) {
        return new User(
                UUID.randomUUID(),
                false,
                LocalDateTime.now(),
                null,
                registerUserRequest.getUsername(),
                passwordEncoder.encode(registerUserRequest.getPassword()), //pitaj
                registerUserRequest.getFullName(),
                Role.ROLE_REPORTER
        );
    }
}
