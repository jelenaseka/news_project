package com.example.news_project.model;

import com.example.news_project.entities.User;
import com.example.news_project.enums.NewsStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponse extends AbstractResponse {
    private String heading;
    private String content;
    private NewsStatus status;
    private User createdBy;
}
