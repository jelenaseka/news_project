package com.example.news_project.tasks;

import com.example.news_project.entities.News;
import com.example.news_project.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public class NewsArchiveTask {
    @Autowired
    private NewsRepository newsRepository;

    @Scheduled(cron = "${scheduler.news.archive}")
    @Transactional
    public void archiveNews() {
        System.out.println("ARCHIVE");
        System.out.println("******************\n\n");
        newsRepository.archiveNewsCreatedAtBefore(LocalDateTime.now());
    }
}
