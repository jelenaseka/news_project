package com.example.news_project.exceptions.domain;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    //TODO delete
//    @ExceptionHandler(ValidationException.class)
//    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
//    public ResponseEntity<Object> handleValidationException(ValidationException exception) {
//        return new ResponseEntity<>(exception.getErrors(), new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
//    }
}
