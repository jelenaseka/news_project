package com.example.news_project.tasks;

import com.example.news_project.entities.SchedulerTaskEntity;
import com.example.news_project.enums.SchedulerTaskKey;
import com.example.news_project.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class NewsArchiveTask extends AbstractTask {

    @Inject
    private NewsRepository newsRepository;

    @Value("${scheduler.news.archive}")
    private String cron;

    @Override
    //TODO sredi ove taskove
//    @Transactional
//    @Scheduled(cron = "${scheduler.news.archive}")
    protected void process() {
        newsRepository.archiveNewsCreatedAtBefore(LocalDateTime.now());
    }

    @Override
    protected SchedulerTaskKey getKey() {
        return SchedulerTaskKey.NEWS_ARCHIVE;
    }

//    @Override
//    public String getCron() {
//        return "'" + cron + "'";
//    }

}
