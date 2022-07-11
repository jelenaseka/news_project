package com.example.news_project.order_specifiers;

import com.example.news_project.entities.QUser;
import com.querydsl.core.types.OrderSpecifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserOrderSpecifier {
    private static final QUser QU = QUser.user;

    public static OrderSpecifier<LocalDateTime> orderByCreatedAtAsc() {
        return QU.createdAt.asc();
    }

    public static OrderSpecifier<LocalDateTime> orderByCreatedAtDesc() {
        return QU.createdAt.desc();
    }

    public static OrderSpecifier<String> orderByUsernameAsc() {
        return QU.username.asc();
    }

    public static OrderSpecifier<String> orderByUsernameDesc() {
        return QU.username.desc();
    }

    public static OrderSpecifier<String> orderByFullNameAsc() {
        return QU.fullName.asc();
    }

    public static OrderSpecifier<String> orderByFullNameDesc() {
        return QU.fullName.desc();
    }

    public static OrderSpecifier<Boolean> orderByIsNotLockedAsc() {
        return QU.isNotLocked.asc();
    }

    public static OrderSpecifier<Boolean> orderByIsNotLockedDesc() {
        return QU.isNotLocked.desc();
    }

    public static OrderSpecifier<Boolean> orderByIsActiveAsc() {
        return QU.isActive.asc();
    }

    public static OrderSpecifier<Boolean> orderByIsActiveDesc() {
        return QU.isActive.desc();
    }

    public static OrderSpecifier<Boolean> orderByIsDeletedAsc() {
        return QU.isDeleted.asc();
    }

    public static OrderSpecifier<Boolean> orderByIsDeletedDesc() {
        return QU.isDeleted.desc();
    }
}
