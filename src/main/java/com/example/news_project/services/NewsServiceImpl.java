package com.example.news_project.services;

import com.example.news_project.entities.News;
import com.example.news_project.enums.NewsEvent;
import com.example.news_project.enums.NewsStatus;
import com.example.news_project.exceptions.NotFoundException;
import com.example.news_project.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Service
public class NewsServiceImpl extends GenericServiceImpl<News, NewsRepository> implements NewsService {
    @Autowired
    private NewsStateHandler newsStateHandler;

    public NewsServiceImpl(NewsRepository newsRepository) {
        super(newsRepository);
    }

    public StateMachine<NewsStatus, NewsEvent> setReady(UUID newsId) {
        return newsStateHandler.sendEvent(newsId, NewsEvent.SET_READY);
    }

    @Override
    public StateMachine<NewsStatus, NewsEvent> accept(UUID newsId) {
        return newsStateHandler.sendEvent(newsId, NewsEvent.ACCEPT);
    }

    @Override
    public StateMachine<NewsStatus, NewsEvent> deny(UUID newsId) {
        return newsStateHandler.sendEvent(newsId, NewsEvent.DENY);
    }


}
