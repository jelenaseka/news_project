package com.example.news_project.security;

import com.example.news_project.enums.Authority;

//add pkg constant
public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {
            "/api/auth/login",
            "/api/auth/register"
    };
    public static final String[] SWAGGER_URLS = { //TODO test
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };
    public static final Authority[] REGISTERED_USER_AUTHORITIES = {
            Authority.NEWS_READ,
            Authority.NEWS_CREATE,
            Authority.NEWS_UPDATE
    };
}
