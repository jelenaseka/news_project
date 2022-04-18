package com.example.news_project.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractEntity {

    @Id
    @Column(columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    protected UUID id;

    @Column(nullable = false)
    protected boolean isDeleted;

    @Column(nullable = false)
    protected LocalDateTime createdAt;

    protected LocalDateTime modifiedAt;

    public AbstractEntity(UUID id, boolean isDeleted, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
