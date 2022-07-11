package com.example.news_project.validators;

import com.example.news_project.model.Errors;
import com.example.news_project.model.RegisterUserRequest;
import com.example.news_project.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class RegisterUserValidator extends GenericValidatorImpl<RegisterUserRequest>{
    @Inject
    private UserRepository userRepository;

    @Override
    protected String getObjectName() {
        return "registerUserRequest";
    }

    @Override
    protected Errors validateFields(Errors errors, RegisterUserRequest registerUserRequest) {
        if(StringUtils.isBlank(registerUserRequest.getUsername())) {
            errors.rejectValue("username", registerUserRequest.getUsername(),"Empty field");
        }
        if(StringUtils.isBlank(registerUserRequest.getPassword())) {
            errors.rejectValue("password", registerUserRequest.getPassword(),"Empty field");
        }
        if(StringUtils.isBlank(registerUserRequest.getFullName())) {
            errors.rejectValue("fullName", registerUserRequest.getFullName(),"Empty field");
        }
        if(userRepository.findByUsername(registerUserRequest.getUsername()).isPresent()) {
            errors.rejectValue("username", registerUserRequest.getUsername(),"Username" + registerUserRequest.getUsername() + " already exists");
        }
        return errors;
    }

}
