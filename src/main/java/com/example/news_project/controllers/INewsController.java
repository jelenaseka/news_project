package com.example.news_project.controllers;

import com.example.news_project.model.NewsFilterParams;
import com.example.news_project.model.NewsRequest;
import com.example.news_project.model.NewsResponse;

public interface INewsController extends IEntityController<NewsRequest, NewsResponse, NewsFilterParams> {

}
