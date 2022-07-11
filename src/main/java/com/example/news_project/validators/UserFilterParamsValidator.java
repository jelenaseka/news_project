package com.example.news_project.validators;

import com.example.news_project.model.Errors;
import com.example.news_project.model.UserFilterParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserFilterParamsValidator extends GenericValidatorImpl<UserFilterParams> {

    @Override
    protected String getObjectName() {
        return "userFilterParams";
    }

    @Override
    protected Errors validateFields(Errors errors, UserFilterParams userFilterParams) {
        //todo check orderBy
        if(userFilterParams.getOrderBy() != null && StringUtils.isBlank(userFilterParams.getOrderBy()) && !isOrderByStringValid(userFilterParams.getOrderBy())) {
            errors.rejectValue("orderBy", userFilterParams.getOrderBy(),"Empty field");
        }

        if(userFilterParams.getPage() < 0)
        {
            errors.rejectValue("page", userFilterParams.getPage(),"Page has negative value");
        }

        //TODO check if this includes null values
        if(userFilterParams.getUsernameContains() != null && StringUtils.isBlank(userFilterParams.getUsernameContains())) {
            errors.rejectValue("usernameContains", userFilterParams.getUsernameContains(),"Empty field");
        }

        if(userFilterParams.getFullNameContains() != null && StringUtils.isBlank(userFilterParams.getFullNameContains())) {
            errors.rejectValue("fullNameContains", userFilterParams.getFullNameContains(),"Empty field");
        }
        return errors;
    }

    private boolean isOrderByStringValid(String orderBy) {
        return !(orderBy.equals("username") || orderBy.equals("fullName")
                || orderBy.equals("isNotLocked") || orderBy.equals("isActive")
                || orderBy.equals("isDeleted") || orderBy.equals("createdAt"));
    }
}
