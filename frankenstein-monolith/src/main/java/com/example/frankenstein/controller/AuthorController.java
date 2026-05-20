package com.example.frankenstein.controller;

import com.example.frankenstein.dto.AuthorRequest;
import com.example.frankenstein.dto.AuthorResponse;
import com.example.frankenstein.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public List<AuthorResponse> listAll() {
        return service.listAll();
    }

    @PostMapping
    public AuthorResponse save(@RequestBody AuthorRequest request) {
        return service.save(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}