package com.example.news_project.enums;

import static com.example.news_project.model.Authority.*;

public enum Role {
    ROLE_PUBLIC(PUBLIC_AUTHORITIES),
    ROLE_REPORTER(REPORTER_AUTHORITIES),
    ROLE_MODERATOR(MODERATOR_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES);

    private String[] authorities; //change to smth that is not string

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
