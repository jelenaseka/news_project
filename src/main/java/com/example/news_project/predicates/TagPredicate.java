package com.example.news_project.predicates;

import com.example.news_project.entities.QTag;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.time.LocalDateTime;

public class TagPredicate {

    private TagPredicate() {}

    public static BooleanExpression matchesCreatedAtBetween(LocalDateTime from, LocalDateTime to) {
        return QTag.tag.createdAt.between(from, to);
    }

    public static BooleanExpression matchesCreatedBefore(LocalDateTime date) {
        return QTag.tag.createdAt.before(date);
    }

    public static BooleanExpression matchesCreatedAfter(LocalDateTime date) {
        return QTag.tag.createdAt.after(date);
    }

    public static BooleanExpression matchesModifiedAtBetween(LocalDateTime from, LocalDateTime to) {
        return QTag.tag.modifiedAt.between(from, to);
    }

    public static BooleanExpression matchesModifiedBefore(LocalDateTime date) {
        return QTag.tag.modifiedAt.before(date);
    }

    public static BooleanExpression matchesModifiedAfter(LocalDateTime date) {
        return QTag.tag.modifiedAt.after(date);
    }

    public static BooleanExpression nameContainsIgnoreCase(String name) {
        return QTag.tag.name.containsIgnoreCase(name);
    }

    public static BooleanExpression matchesName(String name) {
        return QTag.tag.name.eq(name);
    }

    public static BooleanExpression matchesNameIgnoreCase(String name) {
        return QTag.tag.name.equalsIgnoreCase(name);
    }
}
