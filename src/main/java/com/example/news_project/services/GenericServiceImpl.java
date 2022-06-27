package com.example.news_project.services;

import com.example.news_project.entities.AbstractEntity;
import com.example.news_project.entities.News;
import com.example.news_project.repositories.GenericRepository;
import com.example.news_project.services.interfaces.GenericService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public abstract class GenericServiceImpl<ENTITY extends AbstractEntity, REPO extends GenericRepository<ENTITY, UUID>> implements GenericService<ENTITY> {

    private REPO repository;
    protected abstract Predicate getEntityPredicateNotDeleted(UUID id);

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
    public Optional<ENTITY> findByIdAndNotDeleted(UUID id) {
        return repository.findOne(getEntityPredicateNotDeleted(id));
    }
}
