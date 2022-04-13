package com.example.news_project.tasks;

import com.example.news_project.entities.News;
import com.example.news_project.entities.User;
import com.example.news_project.enums.NewsStatus;
import com.example.news_project.repositories.NewsRepository;
import com.example.news_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class NewsCreateTask {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron = "${scheduler.news.create}")
    @Transactional
    public void createNews() {
        UUID id = UUID.randomUUID();
        User user = userRepository.findById(UUID.fromString("9c8c1d85-6fa9-49af-aa31-669f00f4be9b")).get();
        News news = new News(id, false, LocalDateTime.now(), null, "head", "con", NewsStatus.ACCEPTED, user, false);
        newsRepository.save(news);
        List<News> all = newsRepository.findAll();
        for(News n : all) {
            System.out.println(n);
            System.out.println("--------------------------------------------");
        }

    }
}
