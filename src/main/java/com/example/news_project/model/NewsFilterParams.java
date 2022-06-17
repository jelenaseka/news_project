package com.example.news_project.model;

import com.example.news_project.enums.NewsStatus;
import com.example.news_project.enums.SortOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewsFilterParams {
    private String createdBefore;
    private String createdAfter;
    private String modifiedBefore;
    private String modifiedAfter;
    private String headingContains;
    private String contentContains;
    private NewsStatus status;
    private String createdByUsername;
    private String modifiedByUsername;
    private Boolean isArchived;
    private Boolean isDeleted;
    private String orderBy; //pitaj za string il enum il nes drugo
    private SortOrder sortOrder;
    private int page;
}
