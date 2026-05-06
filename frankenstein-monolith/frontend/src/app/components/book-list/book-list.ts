import { Component, OnInit } from '@angular/core';
import { BookService, Book } from '../../services/book';
import { CommonModule } from '@angular/common';


@Component({
    selector: 'app-book-list',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './book-list.html',
    styleUrl: './book-list.css'
})
export class BookListComponent implements OnInit {
    books: Book[] = [];

    constructor(private bookService: BookService) { }

    ngOnInit(): void {
        this.bookService.getBooks().subscribe(data => {
            this.books = data;
        });
    }
}
