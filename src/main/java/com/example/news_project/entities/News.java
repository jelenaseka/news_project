package com.example.news_project.entities;

import com.example.news_project.enums.NewsStatus;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//@SQLDelete(sql = "UPDATE news SET deleted = true WHERE id=?") pitaj za ovo
@Where(clause = "is_deleted=false")
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
