package com.example.news_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that represents error for specific field
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError {
    private String field;
    private Object rejectedValue;
    private String message;
}
