package com.example.spring.catalogservice.web;

import com.example.spring.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        Book book = Book.builder()
                .isbn("1234567890")
                .title("Title")
                .author("Author")
                .price(9.9)
                .build();

        JsonContent<Book> jsonContent = json.write(book);

        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent)
                .extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
    }

    @Test
    void testDeserialize() throws Exception {
        Book book = Book.builder()
                .isbn("1234567890")
                .title("Title")
                .author("Author")
                .price(9.9)
                .build();

        assertThat(book)
                .usingRecursiveComparison()
                .isEqualTo(
                        Book.builder()
                        .isbn("1234567890")
                        .title("Title")
                        .author("Author")
                        .price(9.9)
                        .build()
                );

    }

}