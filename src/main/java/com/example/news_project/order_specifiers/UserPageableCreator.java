package com.example.news_project.order_specifiers;

import com.example.news_project.enums.SortOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class UserPageableCreator {
    @Value("${page.size.users}")
    private int pageSize;

    public Pageable createPageable(String orderBy, SortOrder sortOrder, int page) {
        return null;
    }

}
