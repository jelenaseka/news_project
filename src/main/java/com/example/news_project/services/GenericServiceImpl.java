package com.example.news_project.services;

import com.example.news_project.entities.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public class GenericServiceImpl<ENTITY extends AbstractEntity, REPO extends JpaRepository<ENTITY, UUID>> implements GenericService<ENTITY> {

    private REPO repository;

    @Override
    public ENTITY create(ENTITY e) {
        return repository.save(e);
    }

    @Override
    public void update(ENTITY e) {
        repository.save(e);
    }

    @Override
    public void delete(UUID id) {
        ENTITY entity = repository.findById(id).get();
        entity.setDeleted(true);
        repository.save(entity);
    }

    @Override
    public List<ENTITY> findAll() {
        return repository.findAll();
    }

    @Override
    public ENTITY findById(UUID id) { //da li treba da vratim optional<entity>
        return repository.findById(id).get();
    }
}
