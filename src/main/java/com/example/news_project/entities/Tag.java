package com.example.news_project.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="tags")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tag extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String name;

    public Tag(UUID id, boolean isDeleted, LocalDateTime createdAt, LocalDateTime modifiedAt, String name) {
        super(id, isDeleted, createdAt, modifiedAt);
        this.name = name;
    }
}
