package com.example.news_project.apiservices;

import com.example.news_project.apiservices.interfaces.EntityAPIService;
import com.example.news_project.exceptions.domain.NoContentException;
import com.example.news_project.mappers.Mapper;
import com.example.news_project.services.interfaces.GenericService;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public abstract class EntityApiServiceImpl<ENTITY, ENTITY_REQUEST, ENTITY_RESPONSE, SERVICE extends GenericService<ENTITY>> implements EntityAPIService<ENTITY_REQUEST, ENTITY_RESPONSE> {

    private SERVICE service;
    private Mapper<ENTITY, ENTITY_REQUEST, ENTITY_RESPONSE> mapper;

    /**
     * Sets entities' fields to values of entity request
     * @param entity
     * @param entity_request
     * @return ENTITY
     */
    protected abstract ENTITY setFields(ENTITY entity, ENTITY_REQUEST entity_request);

    protected abstract String getEntityName();

    @Override
    public ENTITY_RESPONSE create(ENTITY_REQUEST entity_request) {
        ENTITY entity = mapper.convertEntityRequestToEntity(entity_request);
        entity = service.create(entity);
        return mapper.convertEntityToEntityResponse(entity);
    }

    @Override
    public void update(UUID id, ENTITY_REQUEST entity_request) {
        Optional<ENTITY> entityMaybe = service.findByIdAndNotDeleted(id);
        if(entityMaybe.isEmpty()) {
            throw new NoContentException(getEntityName() + " with the id " + id + " is not found in the database.");
        }
        ENTITY entity = entityMaybe.get();
        entity = setFields(entity, entity_request);
        service.update(entity);
    }

    @Override
    public void delete(UUID id) {
        Optional<ENTITY> entityMaybe = service.findByIdAndNotDeleted(id);
        if(entityMaybe.isEmpty()) {
            throw new NoContentException(getEntityName() + " with the id " + id + " is not found in the database.");
        }
        service.delete(id);
    }

    @Override
    public ENTITY_RESPONSE findById(UUID id) {
        Optional<ENTITY> entityMaybe = service.findByIdAndNotDeleted(id);
        return entityMaybe.map(entity ->
            {return mapper.convertEntityToEntityResponse(entity);}
            ).orElseThrow(() -> new NoContentException(getEntityName() + " with the id " + id + " is not found in the database."));
    }

    @Override
    public Iterable<ENTITY_RESPONSE> findAllByPredicatePageable(List<Predicate> p, Pageable pageable) {
        Iterable<ENTITY> entityList = service.findAllByPredicatePageable(p, pageable);
        List<ENTITY_RESPONSE> entityResponseList = new ArrayList<>();
        entityList.forEach(e -> entityResponseList.add(mapper.convertEntityToEntityResponse(e)));
        return entityResponseList;
    }
}
