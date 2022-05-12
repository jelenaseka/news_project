package com.example.news_project.config;

import com.example.news_project.tasks.NewsArchiveTask;
import com.example.news_project.tasks.NewsCreateTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Bean
    public NewsArchiveTask newsArchiveTask() {
        return new NewsArchiveTask();
    }

    @Bean
    public NewsCreateTask newsCreateTask() {
        return new NewsCreateTask();
    }

}
