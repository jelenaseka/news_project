package com.example.news_project.order_specifiers;

import com.example.news_project.entities.QNews;
import com.querydsl.core.types.OrderSpecifier;

import java.time.LocalDateTime;

/**
 * Class that contains static methods that returns
 * order specifier depending on value by which news is sorted and in what order.
 */
public class NewsOrderSpecifier {
    private static final QNews QN = QNews.news;

    public static OrderSpecifier<String> orderByHeadingAsc() {
        return QN.heading.asc();
    }

    public static OrderSpecifier<String> orderByHeadingDesc() {
        return QN.heading.desc();
    }

    public static OrderSpecifier<LocalDateTime> orderByCreatedAtAsc() {
        return QN.createdAt.asc();
    }

    public static OrderSpecifier<LocalDateTime> orderByCreatedAtDesc() {
        return QN.createdAt.desc();
    }
    public static OrderSpecifier<LocalDateTime> orderByModifiedAtAsc() {
        return QN.modifiedAt.asc();
    }

    public static OrderSpecifier<LocalDateTime> orderByModifiedAtDesc() {
        return QN.modifiedAt.desc();
    }

}
