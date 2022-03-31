package com.example.news_project.model;

import com.example.news_project.enums.NewsStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequest {
    private String heading;
    private String content;
    private NewsStatus status;
    private UUID createdBy;
}
