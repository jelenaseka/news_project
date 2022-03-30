package com.example.news_project.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
public class News {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String heading;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private NewsStatus status;

    @Column(nullable = false)
    private boolean isDeleted;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(
            name="news_tags",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;
}
