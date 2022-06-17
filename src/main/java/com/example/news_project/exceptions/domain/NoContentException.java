package com.example.news_project.exceptions.domain;

public class NoContentException extends RuntimeException {
    public NoContentException(String msg) {
        super(msg);
    }
}
