package com.example.news_project.validators;

import com.example.news_project.model.Errors;
import com.example.news_project.model.UserRequest;
import com.example.news_project.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class UserRequestValidator extends GenericValidatorImpl<UserRequest>{
    @Inject
    private UserRepository userRepository;

    @Override
    protected String getObjectName() {
        return "userRequest";
    }

    @Override
    protected Errors validateFields(Errors errors, UserRequest userRequest) {
        if(StringUtils.isBlank(userRequest.getUsername())) {
            errors.rejectValue("username", userRequest.getUsername(),"Empty field");
        }
        if(StringUtils.isBlank(userRequest.getPassword())) {
            errors.rejectValue("password", userRequest.getPassword(),"Empty field");
        }
        if(StringUtils.isBlank(userRequest.getFullName())) {
            errors.rejectValue("fullName", userRequest.getFullName(),"Empty field");
        }
        if(userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            errors.rejectValue("username", userRequest.getUsername(),"Username" + userRequest.getUsername() + " already exists");
        }
        return errors;
    }

}
