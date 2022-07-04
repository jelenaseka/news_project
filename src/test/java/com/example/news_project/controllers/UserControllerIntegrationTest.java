package com.example.news_project.controllers;

import com.example.news_project.entities.User;
import com.example.news_project.model.NewsResponse;
import com.example.news_project.model.UserCredentials;
import com.example.news_project.model.UserPrincipal;
import com.example.news_project.model.UserResponse;
import com.example.news_project.repositories.UserRepository;
import com.example.news_project.security.JWTTokenProvider;
import com.example.news_project.security.SecurityConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MvcResult;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest {
    private static final String URL_PREFIX = "/api/users";
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Inject
    private JWTTokenProvider jwtTokenProvider;

    @Test
    public void getOne_ValidId_ReturnedObject() throws Exception {
        String token = login();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        ResponseEntity<UserResponse> res = restTemplate
                .exchange(URL_PREFIX + "/73af28ee-7b3b-4ec1-8a56-26f9a3bbd69c", HttpMethod.GET, new HttpEntity<>(headers), UserResponse.class);
        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    public String login() throws Exception {
        User user = userRepository.findByUsername("sanja").get();
        UserDetails userDetails = new UserPrincipal(user);
        final String token = jwtTokenProvider.generateJwtToken(userDetails);
        return SecurityConstant.TOKEN_PREFIX + token;
    }
}