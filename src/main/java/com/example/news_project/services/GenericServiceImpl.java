package com.example.news_project.services;

import com.example.news_project.entities.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

@AllArgsConstructor
@NoArgsConstructor
public abstract class GenericServiceImpl<ENTITY extends AbstractEntity, REPO extends JpaRepository<ENTITY, UUID>> implements GenericService<ENTITY> {

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
    public Optional<ENTITY> findById(UUID id) {
        return repository.findById(id);
    }
}
