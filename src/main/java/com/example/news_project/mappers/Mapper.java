package com.example.news_project.mappers;

public interface Mapper<ENTITY, ENTITY_REQUEST, ENTITY_RESPONSE> {

    ENTITY convertEntityRequestToEntity(ENTITY_REQUEST entity_request);
    ENTITY_RESPONSE convertEntityToEntityResponse(ENTITY entity);
}
