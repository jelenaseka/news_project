package com.example.news_project.model;

import com.example.news_project.entities.User;
import com.example.news_project.enums.NewsStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponse extends AbstractResponse {
    private String heading;
    private String content;
    private NewsStatus status;
    private UserResponse createdBy;

    public NewsResponse(UUID id, LocalDateTime createdAt, LocalDateTime modifiedAt, String heading, String content, NewsStatus status, UserResponse createdBy) {
        super(id, createdAt, modifiedAt);
        this.heading = heading;
        this.content = content;
        this.status = status;
        this.createdBy = createdBy;
    }
}
