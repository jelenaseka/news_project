package com.example.news_project.validators;

import com.example.news_project.exceptions.domain.ValidationException;
import com.example.news_project.model.Errors;
import com.example.news_project.model.NewsFilterParams;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Component
public class NewsFilterParamsValidator extends GenericValidatorImpl<NewsFilterParams> {

    @Override
    protected String getObjectName() {
        return "newsFilterParams";
    }

    /**
     * Checks if newsFilterParams fields has corresponding values
     * If the field is null then it is not included in search.
     * Otherwise, it is checked if the field satisfies the condition.
     * Date fields containing string must represent LocalDateTime type
     * orderBy field must correspond one of all possible values - "heading","createdAt","modifiedAt"
     * @param newsFilterParams
     */
    @Override
    protected Errors validateFields(Errors errors, NewsFilterParams newsFilterParams) {
        if (checkInputString(newsFilterParams.getCreatedAfter())) {
            errors.rejectValue("createdAfter", newsFilterParams.getCreatedAfter(), "Empty field");
        }
        if (checkInputString(newsFilterParams.getCreatedBefore())) {
            errors.rejectValue("createdBefore", newsFilterParams.getCreatedBefore(),"Empty field");
        }
        if (checkInputString(newsFilterParams.getModifiedBefore())) {
            errors.rejectValue("modifiedBefore", newsFilterParams.getModifiedBefore(),"Empty field");
        }
        if (checkInputString(newsFilterParams.getModifiedAfter())) {
            errors.rejectValue("modifiedAfter",newsFilterParams.getModifiedAfter(), "Empty field");
        }
        if (checkInputString(newsFilterParams.getCreatedByUsername())) {
            errors.rejectValue("createdByUsername", newsFilterParams.getCreatedByUsername(),"Empty field");
        }
        if (checkInputString(newsFilterParams.getModifiedByUsername())) {
            errors.rejectValue("modifiedByUsername", newsFilterParams.getModifiedByUsername(),"Empty field");
        }
        if (checkInputString(newsFilterParams.getOrderBy())) {
            errors.rejectValue("orderBy", newsFilterParams.getOrderBy(),"Empty field");
        }
        if (checkOrderByString(newsFilterParams.getOrderBy())) {
            errors.rejectValue("orderBy", newsFilterParams.getOrderBy(),"Wrong value of orderBy field");
        }

        if(newsFilterParams.getPage() < 0)
        {
            errors.rejectValue("page", newsFilterParams.getPage(),"Page has negative value");
        }

        if(newsFilterParams.getCreatedBefore() != null && !isDate(newsFilterParams.getCreatedBefore())) {
            errors.rejectValue("createdBefore", newsFilterParams.getCreatedBefore(),"Invalid date");
        }
        if(newsFilterParams.getCreatedAfter() != null && !isDate(newsFilterParams.getCreatedAfter())) {
            errors.rejectValue("createdAfter", newsFilterParams.getCreatedAfter(),"Invalid date");
        }
        if(newsFilterParams.getModifiedBefore() != null && !isDate(newsFilterParams.getModifiedBefore())) {
            errors.rejectValue("modifiedBefore", newsFilterParams.getModifiedBefore(),"Invalid date");
        }
        if(newsFilterParams.getModifiedAfter() != null && !isDate(newsFilterParams.getModifiedAfter())) {
            errors.rejectValue("modifiedAfter", newsFilterParams.getModifiedAfter(),"Invalid date");
        }

        if(newsFilterParams.getCreatedBefore() != null && newsFilterParams.getCreatedAfter() != null
                && errors.getFieldError("createdBefore") == null && errors.getFieldError("createdAfter") == null) {
            if(!isDateRangeValid(newsFilterParams.getCreatedBefore(), newsFilterParams.getCreatedAfter())) {
                errors.rejectValue("createdBefore", newsFilterParams.getCreatedBefore(),"Invalid date range");
            }
        }

        if(newsFilterParams.getModifiedBefore() != null && newsFilterParams.getModifiedAfter() != null
                && errors.getFieldError("modifiedBefore") == null && errors.getFieldError("modifiedAfter") == null) {
            if(!isDateRangeValid(newsFilterParams.getModifiedBefore(), newsFilterParams.getModifiedAfter())) {
                errors.rejectValue("modifiedBefore", newsFilterParams.getModifiedBefore(),"Invalid date range");
            }
        }
        return errors;
    }

    private boolean isDateRangeValid(String createdBefore, String createdAfter) {
        return LocalDateTime.parse(createdBefore).isBefore(LocalDateTime.parse(createdAfter));
    }

    private boolean isDate(String dateString) {
        try {
            LocalDateTime.parse(dateString);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private boolean checkOrderByString(String orderBy) {
        if(orderBy == null) return false;
        return !(orderBy.equals("heading") || orderBy.equals("createdAt") || orderBy.equals("modifiedAt"));
    }

    private boolean checkInputString(String input) {
        if(input == null) return false;
        return (input.trim().length() == 0);
    }
}
