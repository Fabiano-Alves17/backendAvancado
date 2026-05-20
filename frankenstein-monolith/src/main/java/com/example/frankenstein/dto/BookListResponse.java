package com.example.frankenstein.dto;

public record BookListResponse(
        Long id,
        String title,
        String authorName
) {
}