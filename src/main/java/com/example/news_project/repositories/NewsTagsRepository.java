package com.example.news_project.repositories;

import com.example.news_project.entities.NewsTags;
import com.example.news_project.entities.NewsTagsKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsTagsRepository extends JpaRepository<NewsTags, NewsTagsKey> {
}
