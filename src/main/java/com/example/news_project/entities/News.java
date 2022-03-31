package com.example.news_project.entities;

import com.example.news_project.enums.NewsStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class News extends AbstractEntity {

    @Column(nullable = false)
    private String heading;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private NewsStatus status;

    @ManyToOne
    private User createdBy;

    public News(UUID id, boolean isDeleted, LocalDateTime createdAt, LocalDateTime modifiedAt, String heading, String content, NewsStatus status, User createdBy) {
        super(id, isDeleted, createdAt, modifiedAt);
        this.heading = heading;
        this.content = content;
        this.status = status;
        this.createdBy = createdBy;
    }

}
