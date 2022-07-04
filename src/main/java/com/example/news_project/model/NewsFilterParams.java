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
public class NewsFilterParams extends AbstractFilterParams {
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

}
