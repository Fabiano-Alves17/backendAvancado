package com.example.frankenstein.dto;

public record LoginRequest(
        String username,
        String password
) {
}