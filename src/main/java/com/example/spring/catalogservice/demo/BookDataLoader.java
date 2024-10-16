package com.example.spring.catalogservice.demo;

import com.example.spring.catalogservice.domain.Book;
import com.example.spring.catalogservice.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
@RequiredArgsConstructor
public class BookDataLoader {

    private final BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadTestData() {
        Book book1 = Book.builder()
                .isbn("1234567890")
                .title("Book 1")
                .author("John Doe")
                .price(9.9)
                .build();

        Book book2 = Book.builder()
                .isbn("1234567891")
                .title("Book 2")
                .author("John Doe2")
                .price(9.9)
                .build();

        bookRepository.save(book1);
        bookRepository.save(book2);
    }

}
