package com.example.news_project.controllers;

import com.example.news_project.enums.SortOrder;
import com.example.news_project.exceptions.domain.NoContentException;
import com.example.news_project.model.NewsFilterParams;
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
    public void getOne_NewsNotExist_NoContent() {
        ResponseEntity<NoContentException> res = restTemplate.getForEntity(URL_PREFIX + "/25635d02-8e08-412b-9b79-2a2eea68a4e7", NoContentException.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
    }

    @Test
    public void createOne_Valid_NewsCreated() {
        NewsRequest newsRequest = new NewsRequest("Indian man jailed for 10 years over wife's 'dowry death'",
                "Dowries, which are illegal but common in India, are wedding gifts given by the bride's family to the groom's family. Kumar had pleaded not guilty.",
                UUID.fromString("c27a894b-65b1-44c5-b1ba-b2a16ae0adad"));
        ResponseEntity<NewsResponse> createdNewsResponse = restTemplate.exchange(URL_PREFIX, HttpMethod.POST, new HttpEntity<>(newsRequest), NewsResponse.class);

        Assertions.assertEquals(HttpStatus.CREATED, createdNewsResponse.getStatusCode());
        Assertions.assertNotNull(createdNewsResponse.getBody());
    }

    @Test
    public void createOne_InvalidCreatedById_NotAcceptable() {
        NewsRequest newsRequest = new NewsRequest("a",
                "a",
                UUID.fromString("5410f281-aba0-4941-95bd-77d410a75a4d"));
        ResponseEntity<String> createdNewsResponse = restTemplate.exchange(URL_PREFIX, HttpMethod.POST, new HttpEntity<>(newsRequest), String.class);

        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, createdNewsResponse.getStatusCode());
        Assertions.assertNotNull(createdNewsResponse.getBody());
    }

    @Test
    public void createOne_InvalidCreatedByRole_NotAcceptable() {
        NewsRequest newsRequest = new NewsRequest("a",
                "a",
                UUID.fromString("9c8c1d85-6fa9-49af-aa31-669f00f4be9b"));
        ResponseEntity<String> createdNewsResponse = restTemplate.exchange(URL_PREFIX, HttpMethod.POST, new HttpEntity<>(newsRequest), String.class);

        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, createdNewsResponse.getStatusCode());
        Assertions.assertNotNull(createdNewsResponse.getBody());
    }

    @Test
    public void updateOne_Valid_NewsUpdated() {
        NewsRequest newsRequest = new NewsRequest("Indian man jailed for 10 years over wife's 'dowry death'",
                "Dowries, which are illegal but common in India, are wedding gifts given by the bride's family to the groom's family. Kumar had pleaded not guilty.",
                UUID.fromString("c27a894b-65b1-44c5-b1ba-b2a16ae0adad"));
        ResponseEntity<NewsResponse> createdNewsResponse = restTemplate.exchange(URL_PREFIX, HttpMethod.POST, new HttpEntity<>(newsRequest), NewsResponse.class);

        ResponseEntity<NewsResponse> updatedNewsResponse = restTemplate.exchange(URL_PREFIX + "/" + createdNewsResponse.getBody().getId(), HttpMethod.PUT, new HttpEntity<>(newsRequest), NewsResponse.class);

        Assertions.assertEquals(HttpStatus.OK, updatedNewsResponse.getStatusCode());
        Assertions.assertNull(updatedNewsResponse.getBody());
    }

    @Test
    public void updateOne_NewsNotExist_NoContent() {
        NewsRequest newsRequest = new NewsRequest("Indian man jailed for 10 years over wife's 'dowry death'",
                "Dowries, which are illegal but common in India, are wedding gifts given by the bride's family to the groom's family. Kumar had pleaded not guilty.",
                UUID.fromString("c27a894b-65b1-44c5-b1ba-b2a16ae0adad"));
        ResponseEntity<NoContentException> updatedNewsResponse = restTemplate.exchange(URL_PREFIX + "/25635d02-8e08-412b-9b79-2a2eea68a4e7", HttpMethod.PUT, new HttpEntity<>(newsRequest), NoContentException.class);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, updatedNewsResponse.getStatusCode());
        Assertions.assertNull(updatedNewsResponse.getBody());
    }

    @Test
    public void updateOne_InvalidRequest_NotAcceptable() {
        NewsRequest newsRequest = new NewsRequest();
        ResponseEntity<String> updatedNewsResponse = restTemplate.exchange(URL_PREFIX + "/25635d02-8e08-412b-9b79-2a2eea68a4e7", HttpMethod.PUT, new HttpEntity<>(newsRequest), String.class);

        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, updatedNewsResponse.getStatusCode());
        Assertions.assertNotNull(updatedNewsResponse.getBody());
    }

    @Test
    public void deleteOne_ValidId_NewsDeleted() {
        NewsRequest newsRequest = new NewsRequest("Indian man jailed for 10 years over wife's 'dowry death'",
                "Dowries, which are illegal but common in India, are wedding gifts given by the bride's family to the groom's family. Kumar had pleaded not guilty.",
                UUID.fromString("9c8c1d85-6fa9-49af-aa31-669f00f4be9b"));
        ResponseEntity<NewsResponse> createdNewsResponse = restTemplate.exchange(URL_PREFIX, HttpMethod.POST, new HttpEntity<>(newsRequest), NewsResponse.class);

        ResponseEntity<Void> updatedNewsResponse = restTemplate.exchange(URL_PREFIX + "/" + createdNewsResponse.getBody().getId(), HttpMethod.DELETE, null, Void.class);
        Assertions.assertEquals(HttpStatus.OK, updatedNewsResponse.getStatusCode());
        Assertions.assertNull(updatedNewsResponse.getBody());
    }

    @Test
    public void deleteOne_NewsNotExist_NoContent() {

        ResponseEntity<Void> updatedNewsResponse = restTemplate.exchange(URL_PREFIX + "/5410f281-aba0-4941-95bd-77d410a75a4d", HttpMethod.DELETE, null, Void.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, updatedNewsResponse.getStatusCode());
        Assertions.assertNull(updatedNewsResponse.getBody());
    }

    @Test
    public void getAll_OrderByCreatedAtDesc() {
        NewsFilterParams newsFilterParams = new NewsFilterParams();
//        newsFilterParams.setOrderBy("createdAt");
//        newsFilterParams.setSortOrder(SortOrder.ASCENDING);
//        newsFilterParams.setPage(0);
        ResponseEntity<NewsResponse[]> createdNewsResponse = restTemplate.exchange(URL_PREFIX + "/filter", HttpMethod.POST, new HttpEntity<>(newsFilterParams), NewsResponse[].class);

        Assertions.assertEquals(HttpStatus.OK, createdNewsResponse.getStatusCode());
        Assertions.assertNotNull(createdNewsResponse.getBody());
    }

    @Test
    public void getAll_OrderByStatusAsc() {
        NewsFilterParams newsFilterParams = new NewsFilterParams();
        newsFilterParams.setOrderBy("status");
        newsFilterParams.setSortOrder(SortOrder.ASCENDING);
//        newsFilterParams.setPage(0);
        ResponseEntity<NewsResponse[]> createdNewsResponse = restTemplate.exchange(URL_PREFIX + "/filter", HttpMethod.POST, new HttpEntity<>(newsFilterParams), NewsResponse[].class);

        Assertions.assertEquals(HttpStatus.OK, createdNewsResponse.getStatusCode());
        Assertions.assertNotNull(createdNewsResponse.getBody());
    }
}