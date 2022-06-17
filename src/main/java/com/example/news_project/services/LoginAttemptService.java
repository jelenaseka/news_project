package com.example.news_project.services;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {
    public static final int MAX_NUMBER_OF_ATTEMPT = 3; //vidi mozda u app prop da stavis
    public static final int ATTEMPT_INCREMENT = 1;
    private LoadingCache<String, Integer> loginAttemptCache;

    /**
     * Initials a cache.
     * Defining a cache that has come from Google.
     * Passing the number of minutes after which we want our cache to expire - 15 min.
     * Defining max size of the cache - supports 100 entries.
     *
     */
    public LoginAttemptService() {
        loginAttemptCache = CacheBuilder.newBuilder()
                .expireAfterWrite(15, TimeUnit.MINUTES)
                .maximumSize(100)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return 0;
                    }
                });
    }

    /**
     * Removing an entry from a cache - no longer need to keep this user in cache
     * @param username
     */
    public void evictUserFromLoginAttemptCache(String username) {
        loginAttemptCache.invalidate(username); //finding key and remove the value
    }

    /**
     * Add user in a cache - taking user's username
     * Increment user's number of attempts, cause if we add them to cache that means they fail to log in
     * @param username
     * @throws ExecutionException
     */
    public void addUserToLoginAttemptCache(String username) {
        int attempts = 0;
        try {
            attempts = ATTEMPT_INCREMENT+ loginAttemptCache.get(username);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        loginAttemptCache.put(username, attempts);
    }

    /**
     * Checks if user has not exceeded the number of time user is allowed to try to log in
     * @param username
     * @return
     * @throws ExecutionException
     */
    public boolean hasExceededMaxAttempts(String username) {
        try {
            return loginAttemptCache.get(username) >= MAX_NUMBER_OF_ATTEMPT;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
