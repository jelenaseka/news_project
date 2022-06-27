package com.example.news_project.validators;

import com.example.news_project.exceptions.domain.UsernameExistException;
import com.example.news_project.exceptions.domain.ValidationException;
import com.example.news_project.model.Errors;
import com.example.news_project.model.RegisterUserRequest;
import com.example.news_project.model.UserRequest;
import com.example.news_project.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

//TODO implement
@Component
public class UserRequestValidator implements EntityRequestValidator<UserRequest>{
    @Inject
    private UserRepository userRepository;


    @Override
    public void validate(UserRequest userRequest) {
        Errors errors = new Errors("userRequest");

        if(StringUtils.isBlank(userRequest.getUsername())) {
            errors.rejectValue("username", userRequest.getUsername(),"Empty field");
        }
        if(StringUtils.isBlank(userRequest.getPassword())) {
            errors.rejectValue("password", userRequest.getPassword(),"Empty field");
        }
        if(StringUtils.isBlank(userRequest.getFullName())) {
            errors.rejectValue("fullName", userRequest.getFullName(),"Empty field");
        }

        //TODO add some restrictions for username and passowrd
        //TODO constant for msg?
        if(userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new UsernameExistException("Username " + userRequest.getUsername() + " already exists");
        }

        if(errors.getErrorCount() > 0) {
            throw new ValidationException(errors);
        }
    }
}
