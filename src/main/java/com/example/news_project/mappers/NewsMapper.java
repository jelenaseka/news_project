package com.example.news_project.mappers;

import com.example.news_project.entities.News;
import com.example.news_project.entities.User;
import com.example.news_project.enums.NewsStatus;
import com.example.news_project.exceptions.domain.NoContentException;
import com.example.news_project.model.NewsRequest;
import com.example.news_project.model.NewsResponse;
import com.example.news_project.model.UserResponse;
import com.example.news_project.repositories.UserRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class NewsMapper implements Mapper<News, NewsRequest, NewsResponse> {
    @Inject
    private UserMapper userMapper;

    @Override
    public News convertEntityRequestToEntity(NewsRequest newsRequest) {
        return new News(
                UUID.randomUUID(),
                false,
                LocalDateTime.now(),
                null,
                newsRequest.getHeading(),
                newsRequest.getContent(),
                NewsStatus.SUBMITTED,
                null,
                null,
                false
        );
    }

    @Override
    public NewsResponse convertEntityToEntityResponse(News news) {
        UserResponse userResponse = userMapper.convertEntityToEntityResponse(news.getCreatedBy());
        return new NewsResponse(
                news.getId(),
                news.getCreatedAt(),
                news.getModifiedAt(),
                news.getHeading(),
                news.getContent(),
                news.getStatus(),
                userResponse
                );
    }
}
