package com.example.news_project.entities;

import com.example.news_project.enums.SchedulerTaskKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Entity that represents executed task
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class SchedulerTaskEntity {
    @Id
    @Enumerated(EnumType.STRING)
    private SchedulerTaskKey id;
    private LocalDateTime executedAt;

}
