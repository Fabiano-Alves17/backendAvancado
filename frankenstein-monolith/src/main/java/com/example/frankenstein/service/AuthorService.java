package com.example.frankenstein.service;

import com.example.frankenstein.dto.AuthorRequest;
import com.example.frankenstein.dto.AuthorResponse;
import com.example.frankenstein.dto.BookResponse;
import com.example.frankenstein.model.Author;
import com.example.frankenstein.model.Book;
import com.example.frankenstein.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<AuthorResponse> listAll() {
        return repository.findAllWithBooks()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AuthorResponse save(AuthorRequest request) {
        validarCpf(request.cpf());

        Author author = new Author();
        author.setName(request.name());
        author.setCpf(request.cpf());
        author.setAnnualIncome(calcularRendaComImposto(request.annualIncome()));

        List<Book> books = new ArrayList<>();

        if (request.books() != null) {
            books = request.books()
                    .stream()
                    .map(bookRequest -> {
                        Book book = new Book();
                        book.setTitle(bookRequest.title());
                        book.setAuthor(author);
                        return book;
                    })
                    .toList();
        }

        author.setBooks(books);

        Author savedAuthor = repository.save(author);

        return toResponse(savedAuthor);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new RuntimeException("CPF Inválido!");
        }
    }

    private Double calcularRendaComImposto(Double annualIncome) {
        if (annualIncome > 50000) {
            return annualIncome * 0.85;
        }

        return annualIncome * 0.93;
    }

    private AuthorResponse toResponse(Author author) {
        List<BookResponse> books = new ArrayList<>();

        if (author.getBooks() != null) {
            books = author.getBooks()
                    .stream()
                    .map(book -> new BookResponse(
                            book.getId(),
                            book.getTitle()
                    ))
                    .toList();
        }

        return new AuthorResponse(
                author.getId(),
                author.getName(),
                author.getCpf(),
                author.getAnnualIncome(),
                books
        );
    }
}