package com.example.news_project.controllers.interfaces;

import com.example.news_project.model.NewsFilterParams;
import com.example.news_project.model.NewsRequest;
import com.example.news_project.model.NewsResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface INewsController extends IEntityController<NewsRequest, NewsResponse, NewsFilterParams> {

    //todo add pathvariable request body itd
    @Override
    @PreAuthorize("hasAnyAuthority('NEWS_READ')")
    Iterable<NewsResponse> findAll(@RequestBody NewsFilterParams newsFilterParams);

    @Override
    @PreAuthorize("hasAnyAuthority('NEWS_READ')")
    NewsResponse findById(UUID id);

    @Override
    @PreAuthorize("hasAnyAuthority('NEWS_CREATE')")
    NewsResponse create(NewsRequest newsRequest);

    @Override
    @PreAuthorize("hasAnyAuthority('NEWS_UPDATE')")
    void update(NewsRequest newsRequest, UUID id);

    @Override
    @PreAuthorize("hasAnyAuthority('NEWS_DELETE')")
    void delete(UUID id);
}
