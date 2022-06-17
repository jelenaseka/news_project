package com.example.news_project.predicates;
import com.example.news_project.entities.QNews;
import com.example.news_project.entities.User;
import com.example.news_project.enums.NewsStatus;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDateTime;

public final class NewsPredicate {

    private static final QNews QN = QNews.news;

    private NewsPredicate() {}

    public static BooleanExpression matchesCreatedAtBetween(LocalDateTime from, LocalDateTime to) {
        return QN.createdAt.between(from, to);
    }

    public static BooleanExpression matchesNotDeleted() {
        return QN.isDeleted.eq(false);
    }

    public static BooleanExpression matchesDeleted(boolean deleted) {
        return QN.isDeleted.eq(deleted);
    }

    public static  BooleanExpression matchesArchived(boolean archived) {
        return QN.isArchived.eq(archived);
    }

    public static BooleanExpression matchesCreatedBefore(LocalDateTime date) {
        return QN.createdAt.before(date);
    }

    public static BooleanExpression matchesCreatedAfter(LocalDateTime date) {
        return QN.createdAt.after(date);
    }

    public static BooleanExpression matchesModifiedAtBetween(LocalDateTime from, LocalDateTime to) {
        return QN.modifiedAt.between(from, to);
    }

    public static BooleanExpression matchesModifiedBefore(LocalDateTime date) {
        return QN.modifiedAt.before(date);
    }

    public static BooleanExpression matchesModifiedAfter(LocalDateTime date) {
        return QN.modifiedAt.after(date);
    }

    public static BooleanExpression headingContainsIgnoreCase(String heading) {
        return QN.heading.containsIgnoreCase(heading);
    }

    public static BooleanExpression contentContainsIgnoreCase(String content) {
        return QN.content.containsIgnoreCase(content);
    }

    public static BooleanExpression matchesCreatedBy(User createdBy) {
        return QN.createdBy.id.eq(createdBy.getId());
    }

    public static BooleanExpression matchesCreatedByUsername(String username) {
        return QN.createdBy.username.eq(username);
    }

    public static BooleanExpression matchesModifiedByUsername(String username) {
        return QN.modifiedBy.username.eq(username);
    }

    public static BooleanExpression matchesModifiedBy(User modifiedBy) {
        return QN.modifiedBy.id.eq(modifiedBy.getId());
    }

    public static BooleanExpression matchesNewsStatus(NewsStatus status) {
        return QN.status.eq(status);
    }
}
