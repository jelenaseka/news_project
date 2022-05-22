package com.example.news_project.services;

import com.example.news_project.entities.News;
import com.example.news_project.enums.NewsEvent;
import com.example.news_project.enums.NewsStatus;
import com.example.news_project.exceptions.NotFoundException;
import com.example.news_project.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NewsStateChangeInterceptor extends StateMachineInterceptorAdapter<NewsStatus, NewsEvent> {

    private final NewsRepository newsRepository;

    @Override
    public void preStateChange(State<NewsStatus, NewsEvent> state, Message<NewsEvent> message, Transition<NewsStatus, NewsEvent> transition, StateMachine<NewsStatus, NewsEvent> stateMachine, StateMachine<NewsStatus, NewsEvent> rootStateMachine) {
        Optional.ofNullable(message).ifPresent(msg -> {
            UUID id = (UUID) msg.getHeaders().getOrDefault("newsId",null);
            if(id != null) {
                Optional<News> newsMaybe = newsRepository.findById(id);
                if(newsMaybe.isEmpty()) {
                    throw new NotFoundException("News with the id + " + id + " is not found in the database.");
                }
                News news = newsMaybe.get();
                news.setStatus(state.getId());
                newsRepository.save(news);
            }
        });
    }
}
