package com.example.news_project.predicates;

import com.example.news_project.model.NewsFilterParams;
import com.example.news_project.predicates.NewsPredicate;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating list of predicates, given the filter parameters by which user wants to filter news
 */
@Component
public class NewsPredicateListCreator {

    /**
     * Returns list of predicates by filter parameters.
     * <p>
     *     If field value is null then it is ignored.
     *     Otherwise it is added to list.
     * </p>
     * @param filterParams
     * @see NewsPredicate
     * @return list of predicates
     */
    public List<Predicate> createPredicates(NewsFilterParams filterParams) {
        List<Predicate> predicateList = new ArrayList<>();
        if(filterParams.getCreatedBefore() != null) {
            predicateList.add(NewsPredicate.matchesCreatedBefore(LocalDateTime.parse(filterParams.getCreatedBefore())));
        }
        if(filterParams.getCreatedAfter() != null) {
            predicateList.add(NewsPredicate.matchesCreatedAfter(LocalDateTime.parse(filterParams.getCreatedAfter())));
        }
        if(filterParams.getModifiedBefore() != null) {
            predicateList.add(NewsPredicate.matchesModifiedBefore(LocalDateTime.parse(filterParams.getModifiedBefore())));
        }
        if(filterParams.getModifiedAfter() != null) {
            predicateList.add(NewsPredicate.matchesModifiedAfter(LocalDateTime.parse(filterParams.getModifiedAfter())));
        }
        if(filterParams.getHeadingContains() != null) {
            predicateList.add(NewsPredicate.headingContainsIgnoreCase(filterParams.getHeadingContains()));
        }
        if(filterParams.getContentContains() != null) {
            predicateList.add(NewsPredicate.contentContainsIgnoreCase(filterParams.getContentContains()));
        }
        if(filterParams.getStatus() != null) {
            predicateList.add(NewsPredicate.matchesNewsStatus(filterParams.getStatus()));
        }
        if(filterParams.getCreatedByUsername() != null) {
            predicateList.add(NewsPredicate.matchesCreatedByUsername(filterParams.getCreatedByUsername()));
        }
        if(filterParams.getModifiedByUsername() != null) {
            predicateList.add(NewsPredicate.matchesModifiedByUsername(filterParams.getModifiedByUsername()));
        }
        if(filterParams.getIsArchived() != null) {
            if(filterParams.getIsArchived()) {
                predicateList.add(NewsPredicate.matchesArchived(true));
            } else {
                predicateList.add(NewsPredicate.matchesArchived(false));
            }
        }

        if(filterParams.getIsDeleted() != null) {
            if(filterParams.getIsDeleted()) {
                predicateList.add(NewsPredicate.matchesDeleted(true));
            } else {
                predicateList.add(NewsPredicate.matchesDeleted(false));
            }
        }
        return predicateList;
    }

}
