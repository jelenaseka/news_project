package com.example.news_project.services;

import com.example.news_project.entities.User;
import com.example.news_project.predicates.UserPredicate;
import com.example.news_project.repositories.UserRepository;
import com.example.news_project.services.interfaces.UserService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserRepository> implements UserService {
    @Inject
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public List<User> findAllByPredicatePageable(List<Predicate> p, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    protected Predicate getEntityPredicateNotDeleted(UUID id) {
        return UserPredicate.matchesNotDeleted().and(UserPredicate.matchesId(id));
    }
}
