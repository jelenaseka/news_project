package com.example.news_project.controllers;

import com.example.news_project.apiservices.interfaces.UserAPIService;
import com.example.news_project.entities.QUser;
import com.example.news_project.enums.Authority;
import com.example.news_project.enums.SortOrder;
import com.example.news_project.model.UserCredentials;
import com.example.news_project.model.UserFilterParams;
import com.example.news_project.model.UserRequest;
import com.example.news_project.model.UserResponse;
import com.example.news_project.order_specifiers.NewsOrderSpecifier;
import com.example.news_project.order_specifiers.UserPageableCreator;
import com.example.news_project.predicates.UserPredicate;
import com.example.news_project.predicates.UserPredicateListCreator;
import com.example.news_project.security.SecurityConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserPredicateListCreator userPredicateListCreator;
    @MockBean
    private UserPageableCreator userPageableCreator;
    @MockBean
    private UserAPIService userAPIService;

    @Test
    public void getOne_ValidId_ObjectReturned() throws Exception {
        String token = login();
        mockMvc.perform(
                get("/api/users/73af28ee-7b3b-4ec1-8a56-26f9a3bbd69c")
                        .header("Authorization", token)
                ).andExpect(status().isOk());
    }

    @Test
    public void getOne_NonExistingId_NoContent() throws Exception {
        String token = login();
        mockMvc.perform(
                get("/api/users/73af28ee-7b4b-4ec1-8a56-26f9a3bbd69c")
                        .header("Authorization", token)
                ).andExpect(status().isNoContent());
    }

    @Test
    public void getOne_InvalidId_BadRequest() throws Exception {
        String token = login();
        mockMvc.perform(
                get("/api/users/a")
                    .header("Authorization", token)
                ).andExpect(status().isBadRequest());

    }

    @Test
    public void findAll_ValidParams_ListReturned() throws Exception {
        String token = login();
        UserFilterParams userFilterParams = new UserFilterParams();
        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(UserPredicate.fullNameContainsIgnoreCase("sanja"));

        Pageable pageable = QPageRequest.of(0, 10, QUser.user.createdAt.desc());
        when(userPredicateListCreator.createPredicates(userFilterParams)).thenReturn(predicateList);
        when(userPageableCreator.createPageable(null, SortOrder.DESCENDING, 0)).thenReturn(pageable);

        mockMvc.perform(
                post("/api/users/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(userFilterParams))
                        .header("Authorization", token)
                    ).andExpect(status().isOk());
    }

    @Test
    public void findAll_EmptyParams_BadRequest() throws Exception {
        String token = login();
        UserFilterParams userFilterParams = new UserFilterParams();
        userFilterParams.setOrderBy("");

        mockMvc.perform(
                post("/api/users/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(userFilterParams))
                        .header("Authorization", token)
        ).andExpect(status().isNotAcceptable());
    }

    @Test
    public void findAll_NullParams_ListReturned() throws Exception {
        String token = login();
        UserFilterParams userFilterParams = new UserFilterParams();
        userFilterParams.setOrderBy(null);

        mockMvc.perform(
                post("/api/users/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(userFilterParams))
                        .header("Authorization", token)
        ).andExpect(status().isOk());
    }

    @Test
    public void createOne_InvalidUserRequest_BadRequest() throws Exception {
        String token = login();
        UserRequest userRequest = new UserRequest();

        mockMvc.perform(
                post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(userRequest))
                        .header("Authorization", token)
        ).andExpect(status().isNotAcceptable());
    }

    @Test
    public void createOne_ValidUserRequest_ObjectCreated() throws Exception {
        String token = login();
        UserRequest userRequest = new UserRequest("milica","milica123","Milica Stojanovic", List.of(Authority.NEWS_CREATE));
        when(userAPIService.create(userRequest)).thenReturn(new UserResponse());

        mockMvc.perform(
                post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(userRequest))
                        .header("Authorization", token)
        ).andExpect(status().isCreated());
    }

    public static String toJSON(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String login() throws Exception {
        UserCredentials userCredentials = new UserCredentials("sanja","sanja123");
        MvcResult result = mockMvc.perform(
                        post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toJSON(userCredentials)))
                .andReturn();
        String token = SecurityConstant.TOKEN_PREFIX + result.getResponse().getHeader(SecurityConstant.JWT_TOKEN_HEADER);
        return token;
    }
}