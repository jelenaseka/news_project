package com.example.news_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<Entity, Key> extends JpaRepository<Entity, Key>, QuerydslPredicateExecutor<Entity> {
}
