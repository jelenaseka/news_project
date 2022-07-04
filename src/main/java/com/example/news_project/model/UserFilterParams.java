package com.example.news_project.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserFilterParams extends AbstractFilterParams {
    private String usernameContains;
    private String fullNameContains;
    private Boolean isNotLocked;
    private Boolean isActive;
}
