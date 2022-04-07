package com.example.news_project.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public class GenericServiceImpl<ENTITY, REPO extends JpaRepository<ENTITY, UUID>> implements GenericService<ENTITY> {

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
        repository.deleteById(id);
    }

    @Override
    public List<ENTITY> findAll() {
        return repository.findAll();
    }

    @Override
    public ENTITY findById(UUID id) {
        return repository.findById(id).get();
    }
}
