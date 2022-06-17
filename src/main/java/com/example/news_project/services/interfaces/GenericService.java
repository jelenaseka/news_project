package com.example.news_project.services.interfaces;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenericService<ENTITY> {

    ENTITY create(ENTITY e);
    void update(ENTITY e);
    void delete(UUID id);
    Optional<ENTITY> findById(UUID id);

    /**
     * Given list of predicates and pageable object, filters all entities
     * @param p
     * @param pageable
     * @return Iterable<ENTITY>
     */
    Iterable<ENTITY> findAllByPredicatePageable(List<Predicate> p, Pageable pageable);
}
