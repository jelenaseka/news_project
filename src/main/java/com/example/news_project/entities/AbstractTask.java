package com.example.news_project.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractTask<ENTITY, REPO extends JpaRepository<ENTITY, String>> {
    @Transient
    protected REPO repository;
    @Id
    private String id;
    private LocalDateTime executedAt;

    public void saveEntity(ENTITY entity) {
        repository.save(entity);
    }
}
