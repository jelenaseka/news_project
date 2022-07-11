package com.example.news_project.apiservices;

import com.example.news_project.apiservices.interfaces.NewsAPIService;
import com.example.news_project.entities.News;
import com.example.news_project.entities.User;
import com.example.news_project.exceptions.domain.ForbiddenException;
import com.example.news_project.exceptions.domain.NoContentException;
import com.example.news_project.mappers.Mapper;
import com.example.news_project.model.NewsRequest;
import com.example.news_project.model.NewsResponse;
import com.example.news_project.security.JwtUserDetailsService;
import com.example.news_project.services.interfaces.NewsService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewsAPIServiceImpl extends EntityApiServiceImpl<News, NewsRequest, NewsResponse, NewsService> implements NewsAPIService {

    @Inject
    private NewsService newsService;
    @Inject
    private Mapper<News, NewsRequest, NewsResponse> newsMapper;
    @Inject
    private JwtUserDetailsService jwtUserDetailsService;

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

    @Override
    public NewsResponse create(NewsRequest newsRequest) {
        News news = newsMapper.convertEntityRequestToEntity(newsRequest);
        User user = jwtUserDetailsService.getLoggedInUser();
        news.setCreatedBy(user);
        news = newsService.create(news);
        return newsMapper.convertEntityToEntityResponse(news);
    }

    @Override
    public void update(UUID id, NewsRequest newsRequest) {
        User user = jwtUserDetailsService.getLoggedInUser();
        Optional<News> newsMaybe = newsService.findById(id);
        if(newsMaybe.isEmpty()) {
            throw new NoContentException(getEntityName() + " with the id " + id + " is not found in the database.");
        }
        News news = newsMaybe.get();
        if(news.getCreatedBy() != user) {
            throw new ForbiddenException("You are not allowed to edit this news");
        }
        news = setFields(news, newsRequest);
        newsService.update(news);
    }

    @Override
    public void delete(UUID id) {
        User user = jwtUserDetailsService.getLoggedInUser();
        Optional<News> newsMaybe = newsService.findById(id);
        if(newsMaybe.isEmpty()) {
            throw new NoContentException(getEntityName() + " with the id " + id + " is not found in the database.");
        }
        News news = newsMaybe.get();
        if(news.getCreatedBy() != user) {
            throw new ForbiddenException("You are not allowed to delete this news");
        }
        newsService.delete(id);
    }
}
