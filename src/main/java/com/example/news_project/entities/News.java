package com.example.news_project.entities;

import com.example.news_project.enums.NewsStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class News extends AbstractEntity {

    @Column(nullable = false)
    private String heading;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private NewsStatus status;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User modifiedBy;

    private boolean isArchived;

    public News(UUID id, boolean isDeleted, LocalDateTime createdAt, LocalDateTime modifiedAt, String heading, String content, NewsStatus status, User createdBy, boolean isArchived) {
        super(id, isDeleted, createdAt, modifiedAt);
        this.heading = heading;
        this.content = content;
        this.status = status;
        this.createdBy = createdBy;
        this.isArchived = isArchived;
    }

}
