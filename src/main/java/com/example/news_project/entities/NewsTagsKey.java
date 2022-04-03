package com.example.news_project.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class NewsTagsKey implements Serializable {

    @Column(columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID newsId;

    @Column(columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID tagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsTagsKey)) return false;
        NewsTagsKey that = (NewsTagsKey) o;
        return Objects.equals(getNewsId(), that.getNewsId()) && Objects.equals(getTagId(), that.getTagId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNewsId(), getTagId());
    }
}
