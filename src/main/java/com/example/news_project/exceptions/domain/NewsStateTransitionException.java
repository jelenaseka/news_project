package com.example.news_project.exceptions.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//TODO ovo ne radi
@ResponseStatus(HttpStatus.CONFLICT)
public class NewsStateTransitionException extends RuntimeException {
    public NewsStateTransitionException(String msg) {
        super(msg);
    }
}
