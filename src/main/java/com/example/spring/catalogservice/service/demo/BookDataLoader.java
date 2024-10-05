package com.example.spring.catalogservice.service.demo;

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
    public void loadBookTestData() {
        Book book1 = new Book("1234567891", "ABC", "AAA", 9.9);
        Book book2 = new Book("1234567892", "DEF", "BBB", 10.0);

        bookRepository.save(book1);
        bookRepository.save(book2);
    }

}
