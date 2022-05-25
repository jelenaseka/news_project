package com.example.news_project.apiservices;

import com.example.news_project.exceptions.NotFoundException;
import com.example.news_project.mappers.Mapper;
import com.example.news_project.services.GenericService;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public abstract class EntityApiServiceImpl<ENTITY, ENTITY_REQUEST, ENTITY_RESPONSE, SERVICE extends GenericService<ENTITY>> implements EntityAPIService<ENTITY_REQUEST, ENTITY_RESPONSE> {

    private SERVICE service;
    private Mapper<ENTITY, ENTITY_REQUEST, ENTITY_RESPONSE> mapper;

    protected abstract ENTITY setFields(ENTITY entity, ENTITY_REQUEST entity_request);
    protected abstract void validation(ENTITY entity);
    protected abstract String getEntityName();

    @Override
    public ENTITY_RESPONSE create(ENTITY_REQUEST entity_request) {
        ENTITY entity = mapper.convertEntityRequestToEntity(entity_request);
        validation(entity);
        entity = service.create(entity);
        return mapper.convertEntityToEntityResponse(entity);
    }

    @Override
    public void update(UUID id, ENTITY_REQUEST e) {
        Optional<ENTITY> entityMaybe = service.findById(id);
        if(entityMaybe.isEmpty()) {
            throw new NotFoundException(getEntityName() + " with the id " + id + " is not found in the database.");
        }
        ENTITY entity = entityMaybe.get();
        validation(entity);
        entity = setFields(entity, e);
        service.update(entity);
    }

    @Override
    public void delete(UUID id) {
        Optional<ENTITY> entityMaybe = service.findById(id);
        if(entityMaybe.isEmpty()) {
            throw new NotFoundException(getEntityName() + " with the id " + id + " is not found in the database.");
        }
        service.delete(id);
    }

    @Override
    public List<ENTITY_RESPONSE> findAll() {
        List<ENTITY> entityList = service.findAll();
        List<ENTITY_RESPONSE> entityResponseList = new ArrayList<>();
        entityList.forEach(e -> entityResponseList.add(mapper.convertEntityToEntityResponse(e)));
        return entityResponseList;
    }

    @Override
    public ENTITY_RESPONSE findById(UUID id) {
        Optional<ENTITY> entityMaybe = service.findById(id);
        if(entityMaybe.isEmpty()) {
            throw new NotFoundException(getEntityName() + " with the id " + id + " is not found in the database.");
        }
        ENTITY entity = entityMaybe.get();
        return mapper.convertEntityToEntityResponse(entity);
    }
}
