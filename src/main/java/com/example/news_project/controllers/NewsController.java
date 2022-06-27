package com.example.news_project.controllers;

import com.example.news_project.apiservices.interfaces.NewsAPIService;
import com.example.news_project.controllers.interfaces.INewsController;
import com.example.news_project.entities.User;
import com.example.news_project.exceptions.domain.ExceptionControllerAdvice;
import com.example.news_project.order_specifiers.NewsPageableCreator;
import com.example.news_project.predicates.NewsPredicateListCreator;
import com.example.news_project.model.NewsFilterParams;
import com.example.news_project.model.NewsRequest;
import com.example.news_project.model.NewsResponse;
import com.example.news_project.security.JwtUserDetailsService;
import com.example.news_project.services.interfaces.UserService;
import com.example.news_project.validators.NewsFilterParamsValidator;
import com.example.news_project.validators.NewsRequestValidator;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * The controller for News REST endpoints
 * <p>
 * Handles the CRUD operations for News object, via HTTP actions.
 * </p>
 *
 */
//TODO add logger everywhere
@RestController
@RequestMapping("/api/news")
public class NewsController extends ExceptionControllerAdvice implements INewsController {
    @Inject
    private NewsAPIService newsAPIService;
    @Inject
    private NewsPredicateListCreator newsPredicateListCreator;
    @Inject
    private NewsRequestValidator newsValidator;
    @Inject
    private NewsFilterParamsValidator newsFilterParamsValidator;
    @Inject
    private NewsPageableCreator newsPageableCreator;


    /**
     * Returns collection of NewsResponse objects based on filterParams
     * @param filterParams JSON representation of object that contains
     *                     parameters for news filtering
     * @return collection of NewsResponse objects
     * @see NewsFilterParams
     */
    public Iterable<NewsResponse> findAll(NewsFilterParams filterParams) {
        newsFilterParamsValidator.validate(filterParams);
        List<Predicate> predicates = newsPredicateListCreator.createPredicates(filterParams);
        Pageable pageable = newsPageableCreator.createPageable(filterParams.getOrderBy(), filterParams.getSortOrder(), filterParams.getPage());
        return newsAPIService.findAllByPredicatePageable(predicates, pageable);
    }

    /**
     * Fetch news with the given id
     * Returns one of the following status codes:
     * 200: news found
     * 204: news not found
     * @param id a unique identifier for the news
     * @return
     */
    public NewsResponse findById(@PathVariable UUID id) {
        return newsAPIService.findById(id);
    }

    /**
     * Create new news object, given the data provided
     * Returns one of the following status codes:
     * 201: news object created
     * 406: request not acceptable
     * @param newsRequest a JSON representation of newsRequest object
     * @return the newly created news object in response format
     */
    public NewsResponse create(@RequestBody NewsRequest newsRequest) {
        newsValidator.validate(newsRequest);
        return newsAPIService.create(newsRequest);
    }

    /**
     * Updates existing news object with the given id
     * Returns one of the following status codes:
     * 200: news object updated
     * 204: news not found
     * @param newsRequest a JSON representation of newsRequest object
     * @param id a unique identifier for the news
     */
    public void update(@RequestBody NewsRequest newsRequest, @PathVariable UUID id) {
        newsValidator.validate(newsRequest);
        newsAPIService.update(id, newsRequest);
    }

    /**
     * Deletes existing news object with the given id
     * Returns one of the following status codes:
     * 200: news object updated
     * 204: news not found
     * @param id a unique identifier for the news
     */
    public void delete(@PathVariable UUID id) {
        newsAPIService.delete(id);
    }
}
