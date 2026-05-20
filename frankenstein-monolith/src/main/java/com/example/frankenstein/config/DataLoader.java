package com.example.frankenstein.config;

import com.example.frankenstein.model.Author;
import com.example.frankenstein.model.Book;
import com.example.frankenstein.repository.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataLoader {


    @Bean
    CommandLineRunner carregarDados(AuthorRepository authorRepository) {
        return args -> {


            if (authorRepository.count() == 0) {


                for (int i = 1; i <= 50; i++) {
                    Author author = new Author();
                    author.setName("Autor " + i);
                    author.setCpf(String.format("%011d", i));
                    author.setAnnualIncome(30000.0 + i * 1000);

                    List<Book> books = new ArrayList<>();


                    for (int j = 1; j <= 3; j++) {
                        Book book = new Book();
                        book.setTitle("Livro " + j + " do Autor " + i);


                        book.setAuthor(author);

                        books.add(book);
                    }


                    author.setBooks(books);

                    authorRepository.save(author);
                }
            }
        };
    }
}