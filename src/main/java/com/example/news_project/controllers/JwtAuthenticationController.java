package com.example.news_project.controllers;

import com.example.news_project.model.JWTResponse;
import com.example.news_project.model.UserCredentials;
import com.example.news_project.security.JWTTokenProvider;
import com.example.news_project.security.JwtTokenUtil;
import com.example.news_project.security.JwtUserDetailsService;
import com.example.news_project.security.SecurityConstant;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {
    @Inject
    private AuthenticationManager authenticationManager;
    @Inject
    private JWTTokenProvider jwtTokenProvider;
    @Inject
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<UserDetails> createAuthenticationToken(@RequestBody UserCredentials userCredentials) throws Exception {
        authenticate(userCredentials.getUsername(), userCredentials.getPassword());
        final UserDetails userDetails = userDetailsService.findByUsername(userCredentials.getUsername());
        final String token = jwtTokenProvider.generateJwtToken(userDetails);
        HttpHeaders jwtHeader = new HttpHeaders();
        jwtHeader.add(SecurityConstant.JWT_TOKEN_HEADER, token);
        //pitaj da li da vracam neki drugi response, ako je problem da se vidi sifra ovde
        //ili samo da vratim token u bodyju
        return new ResponseEntity<>(userDetails, jwtHeader, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
