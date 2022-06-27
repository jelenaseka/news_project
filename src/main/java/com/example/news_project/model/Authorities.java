package com.example.news_project.model;

import com.example.news_project.enums.Authority;

public class Authorities {
    //user_authority tabela...imam i auth u tabeli
    public static final Authority[] REPORTER_AUTHORITIES = {Authority.NEWS_READ, Authority.NEWS_CREATE};
    public static final Authority[] MODERATOR_AUTHORITIES = {Authority.NEWS_READ, Authority.NEWS_CREATE, Authority.NEWS_UPDATE};
    public static final Authority[] ADMIN_AUTHORITIES = {
            Authority.NEWS_READ, Authority.NEWS_CREATE, Authority.NEWS_UPDATE, Authority.NEWS_DELETE,
            Authority.USER_READ, Authority.USER_CREATE, Authority.USER_UPDATE, Authority.USER_DELETE
    };

    //List<Authority>
}
