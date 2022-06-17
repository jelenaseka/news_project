package com.example.news_project.controllers.interfaces;

import com.example.news_project.model.UserCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthenticationController {

    @PostMapping("/login")
    public ResponseEntity<UserDetails> createAuthenticationToken(@RequestBody UserCredentials userCredentials) throws Exception;

}
