package com.example.news_project.services;

import com.example.news_project.entities.News;
import com.example.news_project.enums.NewsEvent;
import com.example.news_project.enums.NewsStatus;
import com.example.news_project.exceptions.domain.NoContentException;
import com.example.news_project.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NewsStateHandlerImpl implements NewsStateHandler {

    @Inject
    private StateMachineFactory<NewsStatus, NewsEvent> factory;
    private final String NEWS_ID_HEADER = "newsId";
    private final NewsRepository newsRepository;
    private final NewsStateChangeInterceptor newsStateChangeInterceptor;

    /**
     * Sends event for changing state of the state machine.
     *
     * @param newsId
     * @param event Possible events are SET_READY, ACCEPT, DENY
     * @see NewsEvent
     * @return StateMachine<NewsStatus, NewsEvent>
     */
    @Override
    public StateMachine<NewsStatus, NewsEvent> sendEvent(UUID newsId, NewsEvent event) {
        StateMachine<NewsStatus, NewsEvent> sm = this.build(newsId);
        Message<NewsEvent> denyMessage = MessageBuilder.withPayload(event)
                .setHeader(NEWS_ID_HEADER, newsId)
                .build();

        sm.sendEvent(Mono.just(denyMessage)).blockLast();
        return sm;
    }

    /**
     * Building state machine before any change is made.
     * Resets state machine to the state of the news that is found in the database by its id
     * @param newsId
     * @return StateMachine<NewsStatus, NewsEvent>
     */
    @Override
    public StateMachine<NewsStatus, NewsEvent> build(UUID newsId) {
        Optional<News> newsMaybe = newsRepository.findById(newsId);
        if(newsMaybe.isEmpty()) {
            // pitaj ili nesto drugo?
            throw new NoContentException("News with the id + " + newsId + " is not found in the database.");
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
