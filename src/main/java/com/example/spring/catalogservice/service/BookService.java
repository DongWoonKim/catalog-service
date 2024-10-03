package com.example.spring.catalogservice.service;

import com.example.spring.catalogservice.domain.Book;
import com.example.spring.catalogservice.domain.BookAlreadyExistException;
import com.example.spring.catalogservice.domain.BookNotFoundException;
import com.example.spring.catalogservice.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow( () -> new BookNotFoundException(isbn) );
    }

    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.isbn()) ) {
            throw new BookAlreadyExistException(book.isbn());
        }

        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    var booToUpdate = new Book(
                            book.isbn(),
                            book.title(),
                            book.author(),
                            book.price()
                    );

                    return bookRepository.save(booToUpdate);
                })
                .orElseGet(() -> addBookToCatalog(book));
    }
}
