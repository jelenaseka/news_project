package com.example.news_project.predicates;
import com.example.news_project.entities.QNews;
import com.example.news_project.entities.User;
import com.example.news_project.enums.NewsStatus;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDateTime;

public class NewsPredicate {

    private NewsPredicate() {}

    public static BooleanExpression matchesCreatedAtBetween(LocalDateTime from, LocalDateTime to) {
        return QNews.news.createdAt.between(from, to);
    }

    public static BooleanExpression matchesCreatedBefore(LocalDateTime date) {
        return QNews.news.createdAt.before(date);
    }

    public static BooleanExpression matchesCreatedAfter(LocalDateTime date) {
        return QNews.news.createdAt.after(date);
    }

    public static BooleanExpression matchesModifiedAtBetween(LocalDateTime from, LocalDateTime to) {
        return QNews.news.modifiedAt.between(from, to);
    }

    public static BooleanExpression matchesModifiedBefore(LocalDateTime date) {
        return QNews.news.modifiedAt.before(date);
    }

    public static BooleanExpression matchesModifiedAfter(LocalDateTime date) {
        return QNews.news.modifiedAt.after(date);
    }

    public static BooleanExpression headingContainsIgnoreCase(String heading) {
        return QNews.news.heading.containsIgnoreCase(heading);
    }

    public static BooleanExpression contentContainsIgnoreCase(String content) {
        return QNews.news.content.containsIgnoreCase(content);
    }

    public static BooleanExpression matchesCreatedBy(User createdBy) {
        return QNews.news.createdBy.id.eq(createdBy.getId());
    }

    public static BooleanExpression matchesModifiedBy(User modifiedBy) {
        return QNews.news.modifiedBy.id.eq(modifiedBy.getId());
    }

    public static BooleanExpression matchesNewsStatus(NewsStatus status) {
        return QNews.news.status.eq(status);
    }
}
