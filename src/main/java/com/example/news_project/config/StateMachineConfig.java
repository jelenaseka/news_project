package com.example.news_project.config;

import com.example.news_project.enums.NewsEvent;
import com.example.news_project.enums.NewsStatus;
import com.example.news_project.exceptions.domain.NewsStateTransitionException;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;
import java.util.UUID;

@Configuration
@EnableStateMachineFactory
@Log
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<NewsStatus, NewsEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<NewsStatus, NewsEvent> config)
            throws Exception {
        StateMachineListenerAdapter<NewsStatus, NewsEvent> adapter = new StateMachineListenerAdapter<>() {
            @Override
            public void stateChanged(State<NewsStatus, NewsEvent> from, State<NewsStatus, NewsEvent> to) {
                log.info(String.format("State changed(from: %s, to: %s", from + "", to + ""));
            }

            @Override
            public void eventNotAccepted(Message<NewsEvent> event) {
                log.info("Event not accepted");
                throw new NewsStateTransitionException("State cannot be changed to " + event.getPayload().name());
            }

            @Override
            public void stateMachineError(StateMachine<NewsStatus, NewsEvent> stateMachine, Exception exception) {

            }
        };
        config
                .withConfiguration()
                .autoStartup(false)
                .listener(adapter);
    }

    @Override
    public void configure(StateMachineStateConfigurer<NewsStatus, NewsEvent> states)
            throws Exception {
                states
                .withStates()
                .initial(NewsStatus.SUBMITTED)
                .stateEntry(NewsStatus.SUBMITTED, new Action<NewsStatus, NewsEvent>() {
                    @Override
                    public void execute(StateContext<NewsStatus, NewsEvent> stateContext) {
                        UUID newsId = (UUID) stateContext.getExtendedState().getVariables().getOrDefault("newsId", null);
                        log.info("News id: " + newsId.toString());
                        log.info("entering submitted state.");
                    }
                })
                .end(NewsStatus.ACCEPTED)
                .end(NewsStatus.DENIED)
                .states(EnumSet.allOf(NewsStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<NewsStatus, NewsEvent> transitions)
            throws Exception {
        transitions
                .withExternal()
                    .source(NewsStatus.SUBMITTED).target(NewsStatus.READY)
                    .event(NewsEvent.SET_READY)
                    .action(fromSubmitterToReadyAction(), fromSubmitterToReadyErrorAction())
                .and().withExternal()
                    .source(NewsStatus.READY).target(NewsStatus.ACCEPTED)
                    .event(NewsEvent.ACCEPT)
                .and().withExternal()
                    .source(NewsStatus.READY).target(NewsStatus.DENIED)
                    .event(NewsEvent.DENY);
    }

    @Bean
    public Action<NewsStatus, NewsEvent> fromSubmitterToReadyAction() {
        return new Action<NewsStatus, NewsEvent>() {

            @Override
            public void execute(StateContext<NewsStatus, NewsEvent> context) {
                System.out.println("action2 executed");
            }
        };
    }

    @Bean
    public Action<NewsStatus, NewsEvent> fromSubmitterToReadyErrorAction() {
        return new Action<NewsStatus, NewsEvent>() {

            @Override
            public void execute(StateContext<NewsStatus, NewsEvent> context) {
                Exception exception = context.getException();
                exception.getMessage();
            }
        };
    }



}
