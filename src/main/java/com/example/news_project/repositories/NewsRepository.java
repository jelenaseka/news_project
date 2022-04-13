package com.example.news_project.repositories;

import com.example.news_project.entities.News;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface NewsRepository extends GenericRepository<News, UUID> {

    @Modifying
    @Query("update News set isArchived = true where createdAt < :time")
    void archiveNewsCreatedAtBefore(LocalDateTime time);
}
