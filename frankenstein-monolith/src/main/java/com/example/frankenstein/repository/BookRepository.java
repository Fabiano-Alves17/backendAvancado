package com.example.frankenstein.repository;

import com.example.frankenstein.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}