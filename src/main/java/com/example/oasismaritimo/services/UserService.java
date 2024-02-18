package com.example.oasismaritimo.services;

import com.example.oasismaritimo.domain.model.User;
import com.example.oasismaritimo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
