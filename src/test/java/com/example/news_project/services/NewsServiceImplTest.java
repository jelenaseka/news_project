package com.example.news_project.services;

import com.example.news_project.entities.News;
import com.example.news_project.enums.NewsEvent;
import com.example.news_project.enums.NewsStatus;
import com.example.news_project.exceptions.NewsStateTransitionException;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Log
@SpringBootTest
class NewsServiceImplTest {

    @Autowired
    private NewsService newsService;
    @Autowired
    private StateMachineFactory factory;

    @Test
    void stateTransition_Test() {
        News news = newsService.create(
                new News(UUID.randomUUID(),
                        false,
                        LocalDateTime.now(),
                        null,
                        "What parents should know about the increase in unexplained hepatitis cases in children",
                        "The US Centers for Disease Control and Prevention is investigassue a health aaccordingly.",
                        NewsStatus.SUBMITTED, null, null, false));
        StateMachine<NewsStatus, NewsEvent> sm = newsService.setReady(news.getId());
        log.info("State after set ready: " + sm.getState().getId().name());
        Assertions.assertEquals(NewsStatus.READY, sm.getState().getId());
        sm = newsService.accept(news.getId());
        log.info("State after accept: " + sm.getState().getId().name());
        Assertions.assertEquals(NewsStatus.ACCEPTED, sm.getState().getId());
    }

    @Test
    void stateTransition_ExceptionThrown() {
        News news = newsService.create(
                new News(UUID.randomUUID(),
                        false,
                        LocalDateTime.now(),
                        null,
                        "What parents should know about the increase in unexplained hepatitis cases in children",
                        "The US Centers for Disease Control and Prevention is investigassue a health aaccordingly.",
                        NewsStatus.SUBMITTED, null, null, false));
//        StateMachine<NewsStatus, NewsEvent> sm = newsService.accept(news.getId());
//        log.info("State after accept: " + sm.getState().getId().name());
        Assertions.assertThrows(NewsStateTransitionException.class, () -> newsService.accept(news.getId()));
    }

}