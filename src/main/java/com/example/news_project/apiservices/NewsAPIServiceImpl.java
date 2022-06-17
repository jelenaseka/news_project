package com.example.news_project.apiservices;

import com.example.news_project.apiservices.interfaces.NewsAPIService;
import com.example.news_project.entities.News;
import com.example.news_project.mappers.Mapper;
import com.example.news_project.model.NewsRequest;
import com.example.news_project.model.NewsResponse;
import com.example.news_project.services.interfaces.NewsService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class NewsAPIServiceImpl extends EntityApiServiceImpl<News, NewsRequest, NewsResponse, NewsService> implements NewsAPIService {

    @Inject
    private NewsService newsService;
    @Inject
    private Mapper<News, NewsRequest, NewsResponse> newsMapper;

    public NewsAPIServiceImpl(NewsService newsService, Mapper<News, NewsRequest, NewsResponse> newsMapper) {
        super(newsService, newsMapper);
    }

    /**
     * @inheritDoc
     * <p>
     *     Sets news heading and content fields, given the data provided
     * </p>
     * @param news
     * @param newsRequest
     * @return updated news
     */
    @Override
    protected News setFields(News news, NewsRequest newsRequest) {
        news.setHeading(newsRequest.getHeading());
        news.setContent(newsRequest.getContent());
        return news;
    }

    /**
     * Returns entity's name defined in entity's class
     * @return name of the entity
     */
    @Override
    protected String getEntityName() {
        return News.ENTITY_NAME;
    }

}
