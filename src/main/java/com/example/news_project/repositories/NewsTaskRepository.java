package com.example.news_project.repositories;

import com.example.news_project.entities.NewsTask;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsTaskRepository extends GenericRepository<NewsTask, String>{
}
