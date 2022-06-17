package com.example.news_project.apiservices.interfaces;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * Layer between REST API and service layer.
 * Receives entity in request format, sends to service and returns object in response format
 * @param <ENTITY_REQUEST>
 * @param <ENTITY_RESPONSE>
 */
public interface EntityAPIService<ENTITY_REQUEST, ENTITY_RESPONSE> {

    /**
     * Sends entity request object to service layer for entity to be created.
     * Returns created object in response format
     * @param e
     * @return ENTITY_RESPONSE
     */
    ENTITY_RESPONSE create(ENTITY_REQUEST e);

    /**
     * Sends given id of the entity to service layer for entity to be updated.
     * @param id an id of the entity that will be updated
     * @param e
     */
    void update(UUID id, ENTITY_REQUEST e);
    /**
     * Sends given id of the entity to service layer for entity to be deleted.
     * @param id an id of the entity that will be deleted
     */
    void delete(UUID id);

    /**
     * Calling service method for fetching entities, given the list of predicates
     * and pageable object.
     * @param p list of predicate objects
     * @param pageable
     * @see Pageable
     * @see Predicate
     * @return collection of entities in response format
     */
    Iterable<ENTITY_RESPONSE> findAllByPredicatePageable(List<Predicate> p, Pageable pageable);

    /**
     * Sends given id of the entity to service layer for entity to be fetched.
     * @param id
     * @return entity of the given id in response format
     */
    ENTITY_RESPONSE findById(UUID id);
}
