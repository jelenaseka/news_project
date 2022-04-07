package com.example.news_project.predicates;

import com.example.news_project.entities.QUser;
import com.example.news_project.enums.Role;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.time.LocalDateTime;

public final class UserPredicate {

    private static final QUser QU = QUser.user;

    private UserPredicate() {}

    public static BooleanExpression matchesCreatedAtBetween(LocalDateTime from, LocalDateTime to) {
        return QU.createdAt.between(from, to);
    }

    public static BooleanExpression matchesCreatedBefore(LocalDateTime date) {
        return QU.createdAt.before(date);
    }

    public static BooleanExpression matchesCreatedAfter(LocalDateTime date) {
        return QU.createdAt.after(date);
    }

    public static BooleanExpression matchesModifiedAtBetween(LocalDateTime from, LocalDateTime to) {
        return QU.modifiedAt.between(from, to);
    }

    public static BooleanExpression matchesModifiedBefore(LocalDateTime date) {
        return QU.modifiedAt.before(date);
    }

    public static BooleanExpression matchesModifiedAfter(LocalDateTime date) {
        return QU.modifiedAt.after(date);
    }

    public static BooleanExpression matchesUsername(String username) {
        return QU.username.eq(username);
    }

    public static BooleanExpression usernameContainsIgnoreCase(String username) {
        return QU.username.containsIgnoreCase(username);
    }

    public static BooleanExpression fullNameContainsIgnoreCase(String fullName) {
        return QU.fullName.containsIgnoreCase(fullName);
    }

    public static BooleanExpression matchesRole(Role role) {
        return QU.role.eq(role);
    }
}