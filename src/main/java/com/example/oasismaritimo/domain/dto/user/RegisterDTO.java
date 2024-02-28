package com.example.oasismaritimo.domain.dto.user;

import com.example.oasismaritimo.domain.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}
