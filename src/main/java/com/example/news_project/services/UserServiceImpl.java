package com.example.news_project.services;

import com.example.news_project.entities.User;
import com.example.news_project.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserRepository> implements UserService {

}
