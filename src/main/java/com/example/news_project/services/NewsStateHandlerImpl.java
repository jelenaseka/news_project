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
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NewsStateHandlerImpl implements NewsStateHandler {

    @Autowired
    private StateMachineFactory<NewsStatus, NewsEvent> factory;
    private final String NEWS_ID_HEADER = "newsId";
    private final NewsRepository newsRepository;
    private final NewsStateChangeInterceptor newsStateChangeInterceptor;

    @Override
    public StateMachine<NewsStatus, NewsEvent> sendEvent(UUID newsId, NewsEvent event) {
        StateMachine<NewsStatus, NewsEvent> sm = this.build(newsId);
        Message<NewsEvent> denyMessage = MessageBuilder.withPayload(event)
                .setHeader(NEWS_ID_HEADER, newsId)
                .build();

        sm.sendEvent(Mono.just(denyMessage)).blockLast();
        return sm;
    }

    @Override
    public StateMachine<NewsStatus, NewsEvent> build(UUID newsId) {
        Optional<News> newsMaybe = newsRepository.findById(newsId);
        if(newsMaybe.isEmpty()) {
            throw new NotFoundException("News with the id + " + newsId + " is not found in the database.");
        }
        News news = newsMaybe.get();

        StateMachine<NewsStatus, NewsEvent> sm = factory.getStateMachine(newsId);
        sm.stopReactively().block();
        sm.getStateMachineAccessor().doWithAllRegions(sma -> {
            sma.addStateMachineInterceptor(newsStateChangeInterceptor);
            sma.resetStateMachineReactively(new DefaultStateMachineContext<>(news.getStatus(), null, null, null)).block();
        });
        sm.startReactively().block();
        return sm;

    }
}
