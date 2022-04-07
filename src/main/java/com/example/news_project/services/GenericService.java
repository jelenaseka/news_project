package com.example.news_project.services;

import java.util.List;
import java.util.UUID;

public interface GenericService<ENTITY> {

    void create(ENTITY e);
    void update(ENTITY e);
    void delete(UUID id);
    List<ENTITY> findAll();
    ENTITY findById();
}
