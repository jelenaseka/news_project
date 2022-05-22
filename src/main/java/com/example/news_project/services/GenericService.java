package com.example.news_project.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenericService<ENTITY> {

    ENTITY create(ENTITY e);
    void update(ENTITY e);
    void delete(UUID id);
    List<ENTITY> findAll();
    Optional<ENTITY> findById(UUID id);
}
