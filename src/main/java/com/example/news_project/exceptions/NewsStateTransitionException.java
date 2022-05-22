package com.example.news_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NewsStateTransitionException extends RuntimeException {
    public NewsStateTransitionException(String msg) {
        super(msg);
    }
}