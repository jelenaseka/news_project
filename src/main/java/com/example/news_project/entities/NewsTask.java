package com.example.news_project.entities;

import com.example.news_project.repositories.NewsTaskRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class NewsTask extends AbstractTask<NewsTask, NewsTaskRepository> {

    public NewsTask(NewsTaskRepository newsTaskRepository, String id, LocalDateTime createdAt) {
        super(newsTaskRepository, id, createdAt);
    }
}
