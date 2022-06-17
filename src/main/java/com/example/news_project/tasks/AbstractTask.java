package com.example.news_project.tasks;

import com.example.news_project.entities.SchedulerTaskEntity;
import com.example.news_project.enums.SchedulerTaskKey;
import com.example.news_project.repositories.SchedulerTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDateTime;

public abstract class AbstractTask {
    Logger logger = LoggerFactory.getLogger(NewsArchiveTask.class);

    @Inject
    private SchedulerTaskRepository schedulerTaskRepository;

    protected void saveSchedulerTask(SchedulerTaskEntity e) {
        schedulerTaskRepository.save(e);
    }

    protected abstract void process();

    protected abstract SchedulerTaskKey getKey();



    @Transactional
//    @Scheduled(cron = "#{@abstractTask.cron()}")
//    @Scheduled(cron = getCron())
//    @Scheduled(cron = "${scheduler.news.archive}")
    protected void execute() {
        logger.info(getKey().toString() + " task called");
        try {
            logger.info(getKey().toString() + " task done");
        } catch (Exception e){
            logger.error("Something went wrong", e);
        } finally {
            saveSchedulerTask(new SchedulerTaskEntity(getKey(), LocalDateTime.now()));
        }
    };




}
