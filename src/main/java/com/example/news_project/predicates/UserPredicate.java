package com.example.news_project.predicates;

import com.example.news_project.entities.QUser;
import com.example.news_project.enums.Role;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.time.LocalDateTime;

public class UserPredicate {

    private UserPredicate() {}

    public static BooleanExpression matchesCreatedAtBetween(LocalDateTime from, LocalDateTime to) {
        return QUser.user.createdAt.between(from, to);
    }

    public static BooleanExpression matchesCreatedBefore(LocalDateTime date) {
        return QUser.user.createdAt.before(date);
    }

    public static BooleanExpression matchesCreatedAfter(LocalDateTime date) {
        return QUser.user.createdAt.after(date);
    }

    public static BooleanExpression matchesModifiedAtBetween(LocalDateTime from, LocalDateTime to) {
        return QUser.user.modifiedAt.between(from, to);
    }

    public static BooleanExpression matchesModifiedBefore(LocalDateTime date) {
        return QUser.user.modifiedAt.before(date);
    }

    public static BooleanExpression matchesModifiedAfter(LocalDateTime date) {
        return QUser.user.modifiedAt.after(date);
    }

    public static BooleanExpression matchesUsername(String username) {
        return QUser.user.username.eq(username);
    }

    public static BooleanExpression usernameContainsIgnoreCase(String username) {
        return QUser.user.username.containsIgnoreCase(username);
    }

    public static BooleanExpression fullNameContainsIgnoreCase(String fullName) {
        return QUser.user.fullName.containsIgnoreCase(fullName);
    }

    public static BooleanExpression matchesRole(Role role) {
        return QUser.user.role.eq(role);
    }
}
