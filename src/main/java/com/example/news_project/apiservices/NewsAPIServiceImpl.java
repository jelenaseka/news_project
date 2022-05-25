package com.example.news_project.apiservices;

import com.example.news_project.entities.News;
import com.example.news_project.mappers.Mapper;
import com.example.news_project.model.NewsRequest;
import com.example.news_project.model.NewsResponse;
import com.example.news_project.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsAPIServiceImpl extends EntityApiServiceImpl<News, NewsRequest, NewsResponse, NewsService> implements NewsAPIService {

    @Autowired
    private NewsService newsService;
    @Autowired
    private Mapper<News, NewsRequest, NewsResponse> newsMapper;

    public NewsAPIServiceImpl(NewsService newsService, Mapper<News, NewsRequest, NewsResponse> newsMapper) {
        super(newsService, newsMapper);
    }

    @Override
    protected News setFields(News news, NewsRequest newsRequest) {
        news.setHeading(newsRequest.getHeading());
        news.setContent(newsRequest.getContent());
        return news;
    }

    @Override
    protected void validation(News news) {

    }

    @Override
    protected String getEntityName() {
        return "News";
    }

}
