package com.example.news_project.controllers;

import com.example.news_project.entities.User;
import com.example.news_project.enums.Authority;
import com.example.news_project.model.*;
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

import java.util.List;

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
    public void register_ValidRequest_UserRegistered() throws Exception {
        String token = loginAsAdmin();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        RegisterUserRequest userRequest = new RegisterUserRequest("","tara123","Tara Damjanovic");
        HttpEntity<RegisterUserRequest> httpEntity = new HttpEntity<>(userRequest, headers);
        ResponseEntity<UserResponse> res = restTemplate
                .exchange(URL_PREFIX , HttpMethod.POST, httpEntity, UserResponse.class);
        Assertions.assertEquals(HttpStatus.CREATED, res.getStatusCode());
        Assertions.assertNotNull(res.getBody());
        Assertions.assertEquals("tara", res.getBody().getUsername());
    }

    @Test
    public void getOne_ValidId_ReturnedObject() throws Exception {
        String token = loginAsAdmin();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        ResponseEntity<UserResponse> res = restTemplate
                .exchange(URL_PREFIX + "/73af28ee-7b3b-4ec1-8a56-26f9a3bbd69c", HttpMethod.GET, new HttpEntity<>(headers), UserResponse.class);
        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

//    @Test
//    public void create_ValidRequest_UserCreated() throws Exception {
//        String token = loginAsAdmin();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", token);
//        UserRequest userRequest = new UserRequest("zorka","zorka123","Zorka Stojanovic",
//                List.of(Authority.NEWS_READ, Authority.NEWS_CREATE, Authority.NEWS_UPDATE, Authority.NEWS_DELETE));
//        ResponseEntity<UserResponse> res = restTemplate
//                .exchange(URL_PREFIX , HttpMethod.POST, new HttpEntity<>(new UserRequest(), headers), UserResponse.class);
//        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
//    }

    public String loginAsModerator() throws Exception {
        User user = userRepository.findByUsername("sanja").get();
        UserDetails userDetails = new UserPrincipal(user);
        final String token = jwtTokenProvider.generateJwtToken(userDetails);
        return SecurityConstant.TOKEN_PREFIX + token;
    }

    public String loginAsAdmin() throws Exception {
        User user = userRepository.findByUsername("seka").get();
        UserDetails userDetails = new UserPrincipal(user);
        final String token = jwtTokenProvider.generateJwtToken(userDetails);
        return SecurityConstant.TOKEN_PREFIX + token;
    }
}