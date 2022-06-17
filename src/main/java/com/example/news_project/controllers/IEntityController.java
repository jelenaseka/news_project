package com.example.news_project.controllers;

import com.example.news_project.model.NewsRequest;
import com.example.news_project.model.NewsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface IEntityController<ENTITY_REQUEST, ENTITY_RESPONSE, FILTER_PARAMS> {

    @PostMapping("/filter")
    Iterable<ENTITY_RESPONSE> findAll(@RequestBody FILTER_PARAMS filterParams);

    @GetMapping("/{id}")
    ENTITY_RESPONSE findById(@PathVariable UUID id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ENTITY_RESPONSE create(@Valid @RequestBody ENTITY_REQUEST entityRequest);

    @PutMapping("/{id}")
    void update(@RequestBody ENTITY_REQUEST entityRequest, @PathVariable UUID id) ;

    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id);
}
