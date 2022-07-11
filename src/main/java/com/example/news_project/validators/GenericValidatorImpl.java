package com.example.news_project.validators;

import com.example.news_project.exceptions.domain.ValidationException;
import com.example.news_project.model.Errors;

public abstract class GenericValidatorImpl<T> implements GenericValidator<T> {

    protected abstract String getObjectName();
    protected abstract Errors validateFields(Errors errors, T t);

    @Override
    public void validate(T t) {
        Errors errors = new Errors(getObjectName());

        errors = validateFields(errors, t);

        if(errors.getErrorCount() > 0) {
            throw new ValidationException(errors);
        }
    }
}
