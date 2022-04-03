package com.example.news_project.repositories;

import com.example.news_project.entities.News;
import com.example.news_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface NewsRepository extends JpaRepository<News, UUID> {
    List<News> findByCreatedBy(User createdBy);
    List<News> findByModifiedBy(User modifiedBy);
    List<News> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to);
}
