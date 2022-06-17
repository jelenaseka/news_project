package com.example.news_project.exceptions.domain;

import com.example.news_project.model.Errors;
import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
    private final Errors errors;

    public ValidationException(Errors errors) {
        super("Validation failed.");
        this.errors = errors;
    }
}
