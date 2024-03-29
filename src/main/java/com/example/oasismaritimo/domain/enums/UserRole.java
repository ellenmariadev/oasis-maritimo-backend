package com.example.oasismaritimo.domain.enums;

public enum UserRole {
    ADMIN("admin"),
    VETERINARIAN("veterinarian"),

    CARETAKER("caretaker"),

    BIOLOGIST("biologist");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
