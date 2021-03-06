package com.example.news_project.entities;

import com.example.news_project.enums.NewsStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name=News.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class News extends AbstractEntity {
    public static final String ENTITY_NAME = "NEWS";
    public static final String TABLE_NAME = "news";

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

    public News(UUID id, boolean isDeleted, LocalDateTime createdAt, LocalDateTime modifiedAt, String heading, String content, NewsStatus status, User createdBy, User modifiedBy, boolean isArchived) {
        super(id, isDeleted, createdAt, modifiedAt);
        this.heading = heading;
        this.content = content;
        this.status = status;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.isArchived = isArchived;
    }

}
