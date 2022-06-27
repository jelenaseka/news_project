package com.example.news_project.services.interfaces;

import com.example.news_project.entities.News;
import com.example.news_project.entities.User;
import com.example.news_project.enums.NewsEvent;
import com.example.news_project.enums.NewsStatus;
import org.springframework.statemachine.StateMachine;

import java.util.Optional;
import java.util.UUID;


public interface NewsService extends GenericService<News> {
    /**
     * changes NewsStatus to READY
     * @param newsId
     * @return StateMachine<NewsStatus, NewsEvent>
     */
    StateMachine<NewsStatus, NewsEvent> setReady(UUID newsId);
    /**
     * changes NewsStatus to ACCEPTED
     * @param newsId
     * @return StateMachine<NewsStatus, NewsEvent>
     */
    StateMachine<NewsStatus, NewsEvent> accept(UUID newsId);
    /**
     * changes NewsStatus to DENIED
     * @param newsId
     * @return StateMachine<NewsStatus, NewsEvent>
     */
    StateMachine<NewsStatus, NewsEvent> deny(UUID newsId);

    Optional<News> findByIdAndCreatedBy(UUID id, User createdBy);
}
