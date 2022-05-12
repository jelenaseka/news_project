package com.example.news_project.services;

import com.example.news_project.entities.News;
import com.example.news_project.enums.NewsEvent;
import com.example.news_project.enums.NewsStatus;
import com.example.news_project.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptor;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Service
public class NewsServiceImpl extends GenericServiceImpl<News, NewsRepository> implements NewsService {

    @Autowired
    private StateMachineFactory<NewsStatus, NewsEvent> factory;
    private final String NEWS_ID_HEADER = "newsId";

    public NewsServiceImpl(NewsRepository newsRepository) {
        super(newsRepository);
    }

    public StateMachine<NewsStatus, NewsEvent> setReady(UUID newsId) {
        return sendEvent(newsId, NewsEvent.SET_READY);
    }

    @Override
    public StateMachine<NewsStatus, NewsEvent> accept(UUID newsId) {
        return sendEvent(newsId, NewsEvent.ACCEPT);
    }

    @Override
    public StateMachine<NewsStatus, NewsEvent> deny(UUID newsId) {
        return sendEvent(newsId, NewsEvent.DENY);
    }

    private StateMachine<NewsStatus, NewsEvent> sendEvent(UUID newsId, NewsEvent event) {
        StateMachine<NewsStatus, NewsEvent> sm = this.build(newsId);
        Message<NewsEvent> denyMessage = MessageBuilder.withPayload(event)
                .setHeader(NEWS_ID_HEADER, newsId)
                .build();

        sm.sendEvent(Mono.just(denyMessage)).blockLast();
        return sm;
    }

    private StateMachine<NewsStatus, NewsEvent> build(UUID newsId) {
        News news = findById(newsId);
        StateMachine<NewsStatus, NewsEvent> sm = factory.getStateMachine(newsId);
        sm.stopReactively().block();
        //sm interceptor to save changed entity
        sm.getStateMachineAccessor().doWithAllRegions(sma -> {
            sma.addStateMachineInterceptor(new StateMachineInterceptorAdapter<>() {
                @Override
                public void preStateChange(State<NewsStatus, NewsEvent> state, Message<NewsEvent> message, Transition<NewsStatus, NewsEvent> transition, StateMachine<NewsStatus, NewsEvent> stateMachine, StateMachine<NewsStatus, NewsEvent> stateMachine1) {
                    Optional.ofNullable(message).ifPresent(msg -> {
                        UUID id = (UUID) msg.getHeaders().getOrDefault("newsId",null);
                        if(id != null) {
                            News news = findById(id);
                            news.setStatus(state.getId());
                            update(news);
                        }
                    });
                }
            });
            sma.resetStateMachineReactively(new DefaultStateMachineContext<>(news.getStatus(), null, null, null)).block();
        });
        sm.startReactively().block();
        return sm;

    }
}
