package com.example.oasismaritimo.services;

import com.example.oasismaritimo.domain.model.User;
import com.example.oasismaritimo.exceptions.NotFoundException;
import com.example.oasismaritimo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDetails findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário"));
        userRepository.deleteById(user.getId());
    }
}
