package com.example.news_project.repositories;

import com.example.news_project.entities.News;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NewsRepository extends GenericRepository<News, UUID> {
}
