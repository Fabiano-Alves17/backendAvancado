package com.example.frankenstein.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnore 
    private Author author;
}
