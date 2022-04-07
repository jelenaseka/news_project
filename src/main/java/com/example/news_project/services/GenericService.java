package com.example.news_project.services;

import com.example.news_project.entities.AbstractEntity;

import java.util.List;
import java.util.UUID;

public interface GenericService<ENTITY> {

    ENTITY create(ENTITY e);
    void update(ENTITY e);
    void delete(UUID id);
    List<ENTITY> findAll();
    ENTITY findById(UUID id);
}
