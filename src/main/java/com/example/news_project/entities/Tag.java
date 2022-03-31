package com.example.news_project.entities;

import javax.persistence.*;

@Entity
@Table(name="tags")
public class Tag extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String name;

}
