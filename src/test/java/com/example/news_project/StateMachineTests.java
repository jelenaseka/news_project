package com.example.news_project;

import com.example.news_project.enums.NewsEvent;
import com.example.news_project.enums.NewsStatus;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

@Log
@SpringBootTest
public class StateMachineTests {

    @Autowired
    StateMachineFactory<NewsStatus, NewsEvent> factory;

//    @Autowired
//    StateMachine<States, Events> sm;

    @Test
    void test() {
        UUID newsId = UUID.fromString("5ad0df9c-e6f9-4f08-9732-dcdc8c74ce57");
        StateMachine<NewsStatus, NewsEvent> sm = factory.getStateMachine(newsId);
        sm.getExtendedState().getVariables().putIfAbsent("newsId", newsId);
        sm.start();
        log.info("Current state: " + sm.getState().getId().name());
        System.out.println(sm.getState().getId().name());
        sm.sendEvent(NewsEvent.SET_READY);
        log.info("Current state: " + sm.getState().getId().name());

        Message<NewsEvent> eventsMessage = MessageBuilder
                .withPayload(NewsEvent.ACCEPT)
                .setHeader("a","b")
                .build();
        sm.sendEvent(eventsMessage);
        log.info("Current state: " + sm.getState().getId().name());
//        Assertions.assertThat(sm.getState().getId())
//                .isEqualTo(NewsStatus.INIT);
    }
//
//    @Test
//    void testChoice() {
//        StateMachine<NewsStatus, NewsEvent> sm = factory.getStateMachine(UUID.randomUUID());
//        sm.start();
//        sm.sendEvent(NewsEvent.CREATE);
//        sm.sendEvent(NewsEvent.SET_READY);
//        sm.sendEvent(NewsEvent.ACCEPT);
//        System.out.println(sm.getState().toString());
//    }

}
