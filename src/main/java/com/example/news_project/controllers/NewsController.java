package com.example.news_project.controllers;

import com.example.news_project.apiservices.NewsAPIService;
import com.example.news_project.apiservices.NewsAPIServiceImpl;
import com.example.news_project.model.NewsRequest;
import com.example.news_project.model.NewsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    private NewsAPIService newsAPIService;

    @GetMapping
    public List<NewsResponse> findAll() {
        return newsAPIService.findAll();
    }

    @GetMapping("/{id}")
    public NewsResponse findById(@PathVariable UUID id) {
        return newsAPIService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsResponse create(@RequestBody NewsRequest newsRequest) {
        return newsAPIService.create(newsRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody NewsRequest newsRequest, @PathVariable UUID id) {
        newsAPIService.update(id, newsRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        newsAPIService.delete(id);
    }
}
