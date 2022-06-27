package com.example.news_project.controllers;

import com.example.news_project.entities.News;
import com.example.news_project.mappers.NewsMapper;
import com.example.news_project.model.NewsFilterParams;
import com.example.news_project.model.NewsRequest;
import com.example.news_project.services.interfaces.NewsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NewsControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsService newsService;

    @MockBean
    private NewsMapper newsMapper;

    @Test
    public void getOne_InvalidId_BadRequest() throws Exception {
        mockMvc.perform(get("/api/news/a")).andExpect(status().isBadRequest());
    }

    @Test
    public void createOne_ValidNewsRequest() throws Exception {
        News news = new News();
        when(newsService.create(news)).thenReturn(news);
        NewsRequest newsRequest = new NewsRequest("heading","content");
        mockMvc.perform(
                post("/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(newsRequest))
        ).andExpect(status().isCreated());
    }

    @Test
    public void createOne_InvalidNewsRequest() throws Exception {
        NewsRequest newsRequest = new NewsRequest();
        mockMvc.perform(
                post("/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(newsRequest))
            ).andExpect(status().isNotAcceptable());
    }


    @Test
    public void createOne_InvalidNewsRequest_EmptyFields() throws Exception {
        NewsRequest newsRequest = new NewsRequest("a","a");
        mockMvc.perform(
                post("/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(newsRequest))
        ).andExpect(status().isNotAcceptable());
    }

    @Test
    public void createOne_InvalidNewsRequest_UUIDisNull() throws Exception {
        mockMvc.perform(
                post("/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"a\",\n" +
                                "    \"heading\":\"a\",\n" +
                                "    \"content\":\"a\",\n" +
                                "    \"status\":\"SUBMITTED\",\n" +
                                "    \"createdBy\":\"a\"}")
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void createOne_InvalidNewsRequest_BlankFields() throws Exception {
        NewsRequest newsRequest = new NewsRequest("    ","    ");
        mockMvc.perform(
                post("/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(newsRequest))
        ).andExpect(status().isNotAcceptable());
    }

    public static String toJSON(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findAll_EmptyFilterParams() throws Exception {
        Pageable pageable = null;
        NewsFilterParams newsFilterParams = new NewsFilterParams();
        when(newsService.findAllByPredicatePageable(new ArrayList<>(),pageable)).thenReturn(new ArrayList<>());
        mockMvc.perform(
                post("/api/news/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(newsFilterParams))
        ).andExpect(status().isOk());
    }

    @Test
    void findAll_FilterParamsBadRequestOrderBy() throws Exception {
        NewsFilterParams newsFilterParams = new NewsFilterParams();
        newsFilterParams.setOrderBy("head");
        mockMvc.perform(
                post("/api/news/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(newsFilterParams))
        ).andExpect(status().isNotAcceptable());
    }

    @Test
    void findAll_FilterParamsBadRequestDateRange() throws Exception {
        NewsFilterParams newsFilterParams = new NewsFilterParams();
        newsFilterParams.setCreatedBefore("2011-12-05T10:15:30");
        newsFilterParams.setCreatedAfter("2011-12-04T10:15:30");
        mockMvc.perform(
                post("/api/news/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(newsFilterParams))
        ).andExpect(status().isNotAcceptable());
    }

    @Test
    void findAll_FilterParamsBadRequestCreatedBefore() throws Exception {
        NewsFilterParams newsFilterParams = new NewsFilterParams();
        newsFilterParams.setCreatedBefore("2011-12-0510:15:30");
        mockMvc.perform(
                post("/api/news/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(newsFilterParams))
        ).andExpect(status().isNotAcceptable());
    }
}