package com.example.oasismaritimo.domain.dto.user;

import com.example.oasismaritimo.domain.model.UserRole;

public record LoginResponseDTO(String token, String username, UserRole role) {
}
