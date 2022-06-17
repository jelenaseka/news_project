package com.example.news_project.enums;

import static com.example.news_project.model.Authorities.*;

public enum Role {
    //ROLE_PUBLIC(PUBLIC_AUTHORITIES), //pitaj - ovo mi ne treba onda
    ROLE_REPORTER(REPORTER_AUTHORITIES),
    ROLE_MODERATOR(MODERATOR_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES);

    private Authority[] authorities;

    Role(Authority... authorities) {
        this.authorities = authorities;
    }

    public Authority[] getAuthorities() {
        return authorities;
    }
}
