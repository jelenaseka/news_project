package com.example.news_project.services.interfaces;

import com.example.news_project.enums.NewsEvent;
import com.example.news_project.enums.NewsStatus;
import org.springframework.statemachine.StateMachine;

import java.util.UUID;

public interface NewsStateHandler {
    StateMachine<NewsStatus, NewsEvent>  sendEvent(UUID newsId, NewsEvent newsEvent);
    StateMachine<NewsStatus, NewsEvent> build(UUID newsId);
}
