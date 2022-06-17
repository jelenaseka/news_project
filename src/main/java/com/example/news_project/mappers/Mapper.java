package com.example.news_project.mappers;

import com.example.news_project.model.RegisterUserRequest;

/**
 * Interface for converting entity to dto and vice versa
 * @param <ENTITY>
 * @param <ENTITY_REQUEST> represents entity in request format in http actions
 * @param <ENTITY_RESPONSE> represents entity in response format in http actions
 */
public interface Mapper<ENTITY, ENTITY_REQUEST, ENTITY_RESPONSE> {

    ENTITY convertEntityRequestToEntity(ENTITY_REQUEST entity_request);
    ENTITY_RESPONSE convertEntityToEntityResponse(ENTITY entity);


}
