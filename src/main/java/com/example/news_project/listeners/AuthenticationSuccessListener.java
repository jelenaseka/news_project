package com.example.news_project.listeners;

import com.example.news_project.model.UserPrincipal;
import com.example.news_project.services.LoginAttemptServiceImpl;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.concurrent.ExecutionException;

@Component
public class AuthenticationSuccessListener {
    @Inject
    private LoginAttemptServiceImpl loginAttemptService;

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) throws ExecutionException {
        Object principal = event.getAuthentication().getPrincipal();
        if(principal instanceof UserPrincipal) {
            UserPrincipal user = (UserPrincipal) event.getAuthentication().getPrincipal();
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }
}
