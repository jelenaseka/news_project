package com.example.news_project.predicates;

import com.example.news_project.entities.News;
import com.example.news_project.entities.QNewsTags;
import com.example.news_project.entities.Tag;
import com.querydsl.core.types.dsl.BooleanExpression;

public class NewsTagsPredicate {

    private NewsTagsPredicate() {}

    public static BooleanExpression matchesNews(News news) {
        return QNewsTags.newsTags.id.newsId.eq(news.getId());
    }

    public static BooleanExpression matchesTag(Tag tag) {
        return QNewsTags.newsTags.id.tagId.eq(tag.getId());
    }
}
