package com.example.news_project.order_specifiers;

import com.example.news_project.enums.SortOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Component;

@Component
public class UserPageableCreator {
    @Value("${page.size.users}")
    private int pageSize;

    public Pageable createPageable(String orderBy, SortOrder sortOrder, int page) {
        if(orderBy == null) {
            return QPageRequest.of(0,pageSize, UserOrderSpecifier.orderByCreatedAtDesc());
        }

        switch (orderBy) {
            case "username":
                if (sortOrder == SortOrder.ASCENDING) {
                    return QPageRequest.of(page, pageSize, UserOrderSpecifier.orderByUsernameAsc());
                } else {
                    return QPageRequest.of(page, pageSize, UserOrderSpecifier.orderByUsernameDesc());
                }
            case "fullName":
                if (sortOrder == SortOrder.ASCENDING) {
                    return QPageRequest.of(page, pageSize, UserOrderSpecifier.orderByFullNameAsc());
                } else {
                    return QPageRequest.of(page, pageSize, UserOrderSpecifier.orderByFullNameDesc());
                }
            case "isNotLocked":
                if (sortOrder == SortOrder.ASCENDING) {
                    return QPageRequest.of(page, pageSize, UserOrderSpecifier.orderByIsNotLockedAsc());
                } else {
                    return QPageRequest.of(page, pageSize, UserOrderSpecifier.orderByIsNotLockedDesc());
                }
            case "isActive":
                if (sortOrder == SortOrder.ASCENDING) {
                    return QPageRequest.of(page, pageSize, UserOrderSpecifier.orderByIsActiveAsc());
                } else {
                    return QPageRequest.of(page, pageSize, UserOrderSpecifier.orderByIsActiveDesc());
                }
            case "isDeleted":
                if (sortOrder == SortOrder.ASCENDING) {
                    return QPageRequest.of(page, pageSize, UserOrderSpecifier.orderByIsDeletedAsc());
                } else {
                    return QPageRequest.of(page, pageSize, UserOrderSpecifier.orderByIsDeletedDesc());
                }
            default:
                if (sortOrder == SortOrder.ASCENDING) {
                    return QPageRequest.of(page, pageSize, UserOrderSpecifier.orderByCreatedAtAsc());
                } else {
                    return QPageRequest.of(page, pageSize, UserOrderSpecifier.orderByCreatedAtDesc());
                }
        }
    }

}
