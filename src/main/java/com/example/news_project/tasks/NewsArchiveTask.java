package com.example.news_project.tasks;

import com.example.news_project.entities.SchedulerTaskEntity;
import com.example.news_project.enums.SchedulerTaskKey;
import com.example.news_project.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

public class NewsArchiveTask extends AbstractTask {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    @Scheduled(cron = "${scheduler.news.archive}")
    @Transactional
    public void execute() {
        logger.info("Archive news task called");
        try {
            newsRepository.archiveNewsCreatedAtBefore(LocalDateTime.now());
            logger.info("Archive news task done");
        } catch (Exception e){
            logger.error("Something went wrong");
        } finally {
            saveSchedulerTask(new SchedulerTaskEntity(SchedulerTaskKey.NEWS_ARCHIVE, LocalDateTime.now()));
        }
    }
}
