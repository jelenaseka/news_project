package com.example.news_project.validators;

import com.example.news_project.exceptions.domain.UsernameExistException;
import com.example.news_project.exceptions.domain.ValidationException;
import com.example.news_project.model.Errors;
import com.example.news_project.model.NewsRequest;
import com.example.news_project.model.RegisterUserRequest;
import com.example.news_project.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class RegisterUserValidator implements EntityRequestValidator<RegisterUserRequest>{
    @Inject
    private UserRepository userRepository;

    @Override
    public void validate(RegisterUserRequest registerUserRequest) {
        Errors errors = new Errors("registerUserRequest");

        if(StringUtils.isBlank(registerUserRequest.getUsername())) {
            errors.rejectValue("username", registerUserRequest.getUsername(),"Empty field");
        }
        if(StringUtils.isBlank(registerUserRequest.getPassword())) {
            errors.rejectValue("password", registerUserRequest.getPassword(),"Empty field");
        }
        if(StringUtils.isBlank(registerUserRequest.getFullName())) {
            errors.rejectValue("fullName", registerUserRequest.getFullName(),"Empty field");
        }

        //TODO add some restrictions for username and passowrd
        //TODO constant for msg?
        if(userRepository.findByUsername(registerUserRequest.getUsername()).isPresent()) {
            throw new UsernameExistException("Username " + registerUserRequest.getUsername() + " already exists");
        }

        if(errors.getErrorCount() > 0) {
            throw new ValidationException(errors);
        }
    }
}
