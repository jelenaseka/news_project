package com.example.news_project.model;

import com.example.news_project.enums.SortOrder;
import lombok.Data;

@Data
public class AbstractFilterParams {
    private Boolean isDeleted;
    private String orderBy;
    private SortOrder sortOrder;
    private int page;
}
