package com.example.oasismaritimo.domain.dto.user;

import com.example.oasismaritimo.domain.enums.UserRole;

import java.util.UUID;

public record LoginResponseDTO(String token, String username, UserRole role, UUID id) {
}
