package com.example.frankenstein.controller;

import com.example.frankenstein.dto.BookListResponse;
import com.example.frankenstein.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<BookListResponse> listAll() {
        return repository.findAll()
                .stream()
                .map(book -> new BookListResponse(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor() != null ? book.getAuthor().getName() : null
                ))
                .toList();
    }
}