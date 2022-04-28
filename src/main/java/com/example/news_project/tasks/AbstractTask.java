package com.example.news_project.tasks;

import com.example.news_project.entities.SchedulerTaskEntity;
import com.example.news_project.repositories.SchedulerTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractTask {
    Logger logger = LoggerFactory.getLogger(NewsArchiveTask.class);

    @Autowired
    private SchedulerTaskRepository schedulerTaskRepository;

    protected void saveSchedulerTask(SchedulerTaskEntity e) {
        schedulerTaskRepository.save(e);
    }

    protected abstract void execute();
}
