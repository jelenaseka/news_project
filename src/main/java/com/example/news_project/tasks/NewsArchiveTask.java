package com.example.news_project.tasks;

import com.example.news_project.entities.NewsTask;
import com.example.news_project.repositories.NewsRepository;
import com.example.news_project.repositories.NewsTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

public class NewsArchiveTask {
    Logger logger = LoggerFactory.getLogger(NewsArchiveTask.class);
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsTaskRepository newsTaskRepository;

    @Scheduled(cron = "${scheduler.news.archive}")
    @Transactional
    public void archiveNews() {
        logger.info("Archive news task called");
        try {
            newsRepository.archiveNewsCreatedAtBefore(LocalDateTime.now());
            NewsTask newsTask = new NewsTask(newsTaskRepository, "NewsArchive", LocalDateTime.now());
            newsTask.saveEntity(newsTask);
            logger.info("Archive news task done");
        } catch (Exception e){
            logger.error("Something went wrong");
        }

    }
}
