package com.example.news_project.controllers;

import com.example.news_project.controllers.interfaces.IAuthenticationController;
import com.example.news_project.model.UserCredentials;
import com.example.news_project.security.JWTTokenProvider;
import com.example.news_project.security.JwtUserDetailsService;
import com.example.news_project.security.SecurityConstant;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

//TODO when user adding news set createdBy by username
//change to enum string in db
//vidi kad se vrata 401 a kad 403 pa  popravi ako nije dobro
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController implements IAuthenticationController {
    @Inject
    private AuthenticationManager authenticationManager;
    @Inject
    private JWTTokenProvider jwtTokenProvider;
    @Inject
    private JwtUserDetailsService userDetailsService;

    public ResponseEntity<UserDetails> createAuthenticationToken(UserCredentials userCredentials) throws Exception {
        authenticate(userCredentials.getUsername(), userCredentials.getPassword());
        final UserDetails userDetails = userDetailsService.findByUsername(userCredentials.getUsername());
        final String token = jwtTokenProvider.generateJwtToken(userDetails);
        HttpHeaders jwtHeader = new HttpHeaders();
        jwtHeader.add(SecurityConstant.JWT_TOKEN_HEADER, token);
        return new ResponseEntity<>(userDetails, jwtHeader, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
