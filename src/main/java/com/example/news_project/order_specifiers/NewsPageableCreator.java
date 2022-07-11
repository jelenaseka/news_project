package com.example.news_project.order_specifiers;

import com.example.news_project.enums.SortOrder;
import com.example.news_project.order_specifiers.NewsOrderSpecifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Component;

/**
 * Class for creating Pageable object
 */
@Component
public class NewsPageableCreator {
    /**
     * Default value for page size if not passed as parameter
     */
    @Value("${page.size.news}")
    private int pageSize; //moze u const

    //pitaj jel se salje i page size

    /**
     * Returns pageable object, given the orderBy value, sort order and page number
     * @param orderBy value by which is collection of news sorted
     * @param sortOrder
     * @param page page number
     * @return pageable object
     * @see Pageable
     */
    public Pageable createPageable(String orderBy, SortOrder sortOrder, int page) {
        if(orderBy == null) {
            return QPageRequest.of(0,pageSize, NewsOrderSpecifier.orderByCreatedAtDesc());
        }
        if(orderBy.equals("heading")) {
            if(sortOrder == SortOrder.ASCENDING) {
                return QPageRequest.of(page,pageSize, NewsOrderSpecifier.orderByHeadingAsc());
            } else {
                return QPageRequest.of(page,pageSize, NewsOrderSpecifier.orderByHeadingDesc());
            }
        } else if(orderBy.equals("createdAt")) {
            if(sortOrder == SortOrder.ASCENDING) {
                return QPageRequest.of(page,pageSize, NewsOrderSpecifier.orderByCreatedAtAsc());
            } else {
                return QPageRequest.of(page,pageSize, NewsOrderSpecifier.orderByCreatedAtDesc());
            }
        } else { //modifiedAt
            if(sortOrder == SortOrder.ASCENDING) {
                return QPageRequest.of(page,pageSize, NewsOrderSpecifier.orderByModifiedAtAsc());
            } else {
                return QPageRequest.of(page,pageSize, NewsOrderSpecifier.orderByModifiedAtDesc());
            }
        }
    }
}
