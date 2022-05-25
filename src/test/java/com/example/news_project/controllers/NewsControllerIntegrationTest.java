package com.example.news_project.controllers;

import com.example.news_project.enums.NewsStatus;
import com.example.news_project.exceptions.NotFoundException;
import com.example.news_project.model.NewsRequest;
import com.example.news_project.model.NewsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NewsControllerIntegrationTest {
    private static final String URL_PREFIX = "/api/news";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getOne_ValidId_ReturnedObject() {
        ResponseEntity<NewsResponse> res = restTemplate.getForEntity(URL_PREFIX + "/00153f57-6a4b-4293-8e59-6e30da787092", NewsResponse.class);
        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void getOne_InvalidId_ExceptionThrown() {
        ResponseEntity<NotFoundException> res = restTemplate.getForEntity(URL_PREFIX + "/fca88816-2bcb-41ed-9441-a8aa6da16709", NotFoundException.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
    }

    @Test
    public void createOne_Valid_NewsCreated() {
        NewsRequest newsRequest = new NewsRequest("Indian man jailed for 10 years over wife's 'dowry death'",
                "Dowries, which are illegal but common in India, are wedding gifts given by the bride's family to the groom's family. Kumar had pleaded not guilty.",
                NewsStatus.SUBMITTED, UUID.fromString("9c8c1d85-6fa9-49af-aa31-669f00f4be9b"));
        ResponseEntity<NewsResponse> createdNewsResponse = restTemplate.exchange(URL_PREFIX, HttpMethod.POST, new HttpEntity<>(newsRequest), NewsResponse.class);

        Assertions.assertEquals(HttpStatus.CREATED, createdNewsResponse.getStatusCode());
        Assertions.assertNotNull(createdNewsResponse.getBody());
    }

    @Test
    public void updateOne_Valid_NewsUpdated() {
        NewsRequest newsRequest = new NewsRequest("Indian man jailed for 10 years over wife's 'dowry death'",
                "Dowries, which are illegal but common in India, are wedding gifts given by the bride's family to the groom's family. Kumar had pleaded not guilty.",
                NewsStatus.SUBMITTED, UUID.fromString("9c8c1d85-6fa9-49af-aa31-669f00f4be9b"));
        ResponseEntity<NewsResponse> createdNewsResponse = restTemplate.exchange(URL_PREFIX, HttpMethod.POST, new HttpEntity<>(newsRequest), NewsResponse.class);

        ResponseEntity<NewsResponse> updatedNewsResponse = restTemplate.exchange(URL_PREFIX + "/" + createdNewsResponse.getBody().getId(), HttpMethod.PUT, new HttpEntity<>(newsRequest), NewsResponse.class);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, updatedNewsResponse.getStatusCode());
        Assertions.assertNull(updatedNewsResponse.getBody());
    }

    @Test
    public void deleteOne_ValidId_NewsDeleted() {
        NewsRequest newsRequest = new NewsRequest("Indian man jailed for 10 years over wife's 'dowry death'",
                "Dowries, which are illegal but common in India, are wedding gifts given by the bride's family to the groom's family. Kumar had pleaded not guilty.",
                NewsStatus.SUBMITTED, UUID.fromString("9c8c1d85-6fa9-49af-aa31-669f00f4be9b"));
        ResponseEntity<NewsResponse> createdNewsResponse = restTemplate.exchange(URL_PREFIX, HttpMethod.POST, new HttpEntity<>(newsRequest), NewsResponse.class);

        ResponseEntity<Void> updatedNewsResponse = restTemplate.exchange(URL_PREFIX + "/" + createdNewsResponse.getBody().getId(), HttpMethod.DELETE, null, Void.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, updatedNewsResponse.getStatusCode());
        Assertions.assertNull(updatedNewsResponse.getBody());
    }
}