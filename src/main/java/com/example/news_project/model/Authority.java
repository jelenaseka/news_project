package com.example.news_project.model;

public class Authority {
    public static final String[] PUBLIC_AUTHORITIES = {"news:read"};
    public static final String[] REPORTER_AUTHORITIES = {"news:read", "news:create"};
    public static final String[] MODERATOR_AUTHORITIES = {"news:read", "news:create", "news:update"};
    public static final String[] ADMIN_AUTHORITIES = {"news:read", "news:create", "news:update", "news:delete"};
}
