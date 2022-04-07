package com.example.news_project.predicates;

import com.example.news_project.entities.QTag;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.time.LocalDateTime;

public class TagPredicate {

    private static final QTag QT = QTag.tag;

    private TagPredicate() {}

    public static BooleanExpression matchesCreatedAtBetween(LocalDateTime from, LocalDateTime to) {
        return QT.createdAt.between(from, to);
    }

    public static BooleanExpression matchesCreatedBefore(LocalDateTime date) {
        return QT.createdAt.before(date);
    }

    public static BooleanExpression matchesCreatedAfter(LocalDateTime date) {
        return QT.createdAt.after(date);
    }

    public static BooleanExpression matchesModifiedAtBetween(LocalDateTime from, LocalDateTime to) {
        return QT.modifiedAt.between(from, to);
    }

    public static BooleanExpression matchesModifiedBefore(LocalDateTime date) {
        return QT.modifiedAt.before(date);
    }

    public static BooleanExpression matchesModifiedAfter(LocalDateTime date) {
        return QT.modifiedAt.after(date);
    }

    public static BooleanExpression nameContainsIgnoreCase(String name) {
        return QT.name.containsIgnoreCase(name);
    }

    public static BooleanExpression matchesName(String name) {
        return QT.name.eq(name);
    }

    public static BooleanExpression matchesNameIgnoreCase(String name) {
        return QT.name.equalsIgnoreCase(name);
    }
}
