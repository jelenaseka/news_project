package com.example.news_project.predicates;

import com.example.news_project.model.NewsFilterParams;
import com.example.news_project.model.UserFilterParams;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserPredicateListCreator {

    public List<Predicate> createPredicates(UserFilterParams filterParams) {
        List<Predicate> predicateList = new ArrayList<>();

        if(filterParams.getUsernameContains() != null) {
            predicateList.add(UserPredicate.usernameContainsIgnoreCase(filterParams.getUsernameContains()));
        }
        if(filterParams.getFullNameContains() != null) {
            predicateList.add(UserPredicate.fullNameContainsIgnoreCase(filterParams.getFullNameContains()));
        }
        if(filterParams.getIsNotLocked() != null) {
            if(filterParams.getIsNotLocked()) {
                predicateList.add(UserPredicate.matchesNotLocked(true));
            } else {
                predicateList.add(UserPredicate.matchesNotLocked(false));
            }
        }
        if(filterParams.getIsActive() != null) {
            if(filterParams.getIsActive()) {
                predicateList.add(UserPredicate.matchesActive(true));
            } else {
                predicateList.add(UserPredicate.matchesActive(false));
            }
        }
        if(filterParams.getIsDeleted() != null) {
            if(filterParams.getIsDeleted()) {
                predicateList.add(UserPredicate.matchesDeleted(true));
            } else {
                predicateList.add(UserPredicate.matchesDeleted(false));
            }
        }
        return predicateList;
    }
}
