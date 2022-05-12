package com.example.news_project.services;

import com.example.news_project.entities.News;
import com.example.news_project.enums.NewsEvent;
import com.example.news_project.enums.NewsStatus;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface NewsService extends GenericService<News> {
    StateMachine<NewsStatus, NewsEvent> setReady(UUID newsId);
    StateMachine<NewsStatus, NewsEvent> accept(UUID newsId);
    StateMachine<NewsStatus, NewsEvent> deny(UUID newsId);
}
