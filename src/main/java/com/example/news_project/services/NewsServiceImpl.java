package com.example.news_project.services;

import com.example.news_project.entities.News;
import com.example.news_project.entities.User;
import com.example.news_project.enums.NewsEvent;
import com.example.news_project.enums.NewsStatus;
import com.example.news_project.predicates.NewsPredicate;
import com.example.news_project.repositories.NewsRepository;
import com.example.news_project.services.interfaces.NewsService;
import com.example.news_project.services.interfaces.NewsStateHandler;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewsServiceImpl extends GenericServiceImpl<News, NewsRepository> implements NewsService {
    @Inject
    private NewsStateHandler newsStateHandler;
    @Inject
    private NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        super(newsRepository);
    }

    public StateMachine<NewsStatus, NewsEvent> setReady(UUID newsId) {
        return newsStateHandler.sendEvent(newsId, NewsEvent.SET_READY);
    }

    @Override
    public StateMachine<NewsStatus, NewsEvent> accept(UUID newsId) {
        return newsStateHandler.sendEvent(newsId, NewsEvent.ACCEPT);
    }

    @Override
    public StateMachine<NewsStatus, NewsEvent> deny(UUID newsId) {
        return newsStateHandler.sendEvent(newsId, NewsEvent.DENY);
    }

    @Override
    public Optional<News> findById(UUID id) {
        return newsRepository.findById(id);
    }

    @Override
    public Iterable<News> findAllByPredicatePageable(List<Predicate> p, Pageable pageable) {
        if(p.size() > 0) {
            return newsRepository.findAll(ExpressionUtils.allOf(p), pageable);
        } else {
            return newsRepository.findAll(pageable);
        }
    }

    //todo pogledaj za newsTag
    @Override
    protected Predicate getEntityPredicateNotDeleted(UUID id) {
        return NewsPredicate.matchesDeleted(false).and(NewsPredicate.matchesId(id));
    }
}
