package com.example.news_project.validators;

/**
 * Interface for validation ENTITY_REQUEST object - JSON representation of Entity object
 */
public interface EntityRequestValidator<ENTITY_REQUEST> {
    /**
     * Validates entity request that represents request body of http request
     * @param entity_request
     */
    void validate(ENTITY_REQUEST entity_request);
}
