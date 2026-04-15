package com.example.frankenstein.repository;

import com.example.frankenstein.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
