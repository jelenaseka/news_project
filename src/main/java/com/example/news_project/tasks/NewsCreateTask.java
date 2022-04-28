package com.example.news_project.tasks;

import com.example.news_project.entities.News;
import com.example.news_project.entities.SchedulerTaskEntity;
import com.example.news_project.entities.User;
import com.example.news_project.enums.NewsStatus;
import com.example.news_project.enums.SchedulerTaskKey;
import com.example.news_project.repositories.NewsRepository;
import com.example.news_project.repositories.UserRepository;
import com.example.news_project.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

public class NewsCreateTask extends AbstractTask {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Scheduled(cron = "${scheduler.news.create}")
    @Transactional
    protected void execute() {
        logger.info("Create news task called");

        try {
            UUID id = UUID.randomUUID();
            User user = userRepository.findById(UUID.fromString("9c8c1d85-6fa9-49af-aa31-669f00f4be9b")).get();

            for(int i = 0; i < 10; i++) {
                News news = new News(id, false, LocalDateTime.now(), null, Generator.generateRandomString(5), Generator.generateRandomString(20), NewsStatus.ACCEPTED, user, false);
                newsRepository.save(news);
            }
        } catch (Exception e) {
            logger.error("Something went wrong");
        } finally {
            saveSchedulerTask(new SchedulerTaskEntity(SchedulerTaskKey.NEWS_CREATE, LocalDateTime.now()));
        }

        logger.info("Create news task done");
    }
}
