package com.example.news_project.validators;

import com.example.news_project.exceptions.domain.ValidationException;
import com.example.news_project.model.Errors;
import com.example.news_project.model.NewsFilterParams;
import com.example.news_project.model.UserFilterParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserFilterParamsValidator {

    public void validate(UserFilterParams userFilterParams) {
        Errors errors = new Errors("userFilterParams");

        if(userFilterParams.getOrderBy() != null && StringUtils.isBlank(userFilterParams.getOrderBy())) {
            errors.rejectValue("orderBy", userFilterParams.getOrderBy(),"Empty field");
        }
        //TODO check if this includes null values
        if(userFilterParams.getUsernameContains() != null && StringUtils.isBlank(userFilterParams.getUsernameContains())) {
            errors.rejectValue("usernameContains", userFilterParams.getUsernameContains(),"Empty field");
        }

        if(userFilterParams.getFullNameContains() != null && StringUtils.isBlank(userFilterParams.getFullNameContains())) {
            errors.rejectValue("fullNameContains", userFilterParams.getFullNameContains(),"Empty field");
        }

        if(errors.getErrorCount() > 0) {
            throw new ValidationException(errors);
        }
    }
}
