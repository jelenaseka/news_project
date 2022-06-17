package com.example.news_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents errors for object and contains object name
 * and list of errors for that object.
 * @see ValidationError
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Errors {
    private String objectName;
    private List<ValidationError> errors;

    public Errors(String objectName) {
        this.objectName = objectName;
        errors = new ArrayList<>();
    }

    public void rejectValue(String field, Object rejectedValue, String message) {
        this.errors.add(new ValidationError(field, rejectedValue, message));
    }

    public ValidationError getFieldError(String fieldName) {
        return this.errors.stream().filter(err -> err.getField().equals(fieldName)).findFirst().orElse(null);
    }

    public int getErrorCount() {
        return this.errors.size();
    }
}
