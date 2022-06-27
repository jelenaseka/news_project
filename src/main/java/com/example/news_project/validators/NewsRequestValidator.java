package com.example.news_project.validators;

import com.example.news_project.entities.User;
import com.example.news_project.exceptions.domain.ValidationException;
import com.example.news_project.model.Errors;
import com.example.news_project.model.NewsRequest;
import com.example.news_project.repositories.UserRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Component that validates NewsRequest object - JSON representation of News object
 */
@Component
public class NewsRequestValidator implements EntityRequestValidator<NewsRequest> {
    @Inject
    private UserRepository userRepository;

    /**
     * @inheritDoc
     * Checks if newsRequest has blank fields
     * @param newsRequest
     */
    @Override
    public void validate(NewsRequest newsRequest) {
        Errors errors = new Errors("newsRequest");

        if (checkInputString(newsRequest.getContent())) {
            errors.rejectValue("content", newsRequest.getContent(),"Empty field");
        }

        if (checkInputString(newsRequest.getHeading())) {
            errors.rejectValue("heading", newsRequest.getHeading(),"Empty field");
        }

        //TODO prebaci ove iste stvari u apstrakciju
        if(errors.getErrorCount() > 0) {
            throw new ValidationException(errors);
        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }

}
