package com.example.news_project.security;

import com.example.news_project.entities.User;
import com.example.news_project.model.UserPrincipal;
import com.example.news_project.repositories.UserRepository;
import com.example.news_project.services.LoginAttemptServiceImpl;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

//TODO add logs
//TODO add email
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Inject
    private UserRepository userRepository;
    @Inject
    private LoginAttemptServiceImpl loginAttemptService;

    public UserDetails findByUsername(String username) {
        User user = userRepository.findByUsername(username).get();
        return new UserPrincipal(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userMaybe = userRepository.findByUsername(username);
        if(userMaybe.isEmpty()) {
            throw new UsernameNotFoundException("Bad credentials.");
        }
        User user = userMaybe.get();
        validateLoginAttempt(user);
        return new UserPrincipal(user);
    }

    private void validateLoginAttempt(User user) {
        if(user.isNotLocked()) {
            if(loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
                user.setNotLocked(false);
            } else {
                user.setNotLocked(true); //not necessary
            }
            userRepository.save(user);
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userRepository.findByUsername(currentUserName).get();
        }
        return null;
    }
}
