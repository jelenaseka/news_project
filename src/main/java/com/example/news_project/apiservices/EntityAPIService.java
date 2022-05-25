package com.example.news_project.apiservices;

import java.util.List;
import java.util.UUID;

public interface EntityAPIService<ENTITY_REQUEST, ENTITY_RESPONSE> {

    ENTITY_RESPONSE create(ENTITY_REQUEST e);
    void update(UUID id, ENTITY_REQUEST e);
    void delete(UUID id);
    List<ENTITY_RESPONSE> findAll();
    ENTITY_RESPONSE findById(UUID id);
}
