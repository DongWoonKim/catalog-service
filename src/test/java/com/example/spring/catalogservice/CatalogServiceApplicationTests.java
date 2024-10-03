package com.example.spring.catalogservice;

import com.example.spring.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {

    @Autowired
    private WebTestClient webClient;

    @Test
    void whenPostRequestThenBookCreated() {
        // given
        Book expectedBook = new Book("1234567890", "Title", "Author", 9.0);

        // when
        webClient.post()
                .uri("/books")
                .bodyValue(expectedBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class)
                .value(actualBook -> {
                    // then
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook.isbn())
                           .isEqualTo(expectedBook.isbn());
                });


    }

}
