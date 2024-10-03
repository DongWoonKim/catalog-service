package com.example.spring.catalogservice.web;

import com.example.spring.catalogservice.controller.BookController;
import com.example.spring.catalogservice.domain.BookNotFoundException;
import com.example.spring.catalogservice.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
class BookControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void wheGetBookNotExistingThenShouldReturnNotFound() throws Exception {
        // given
        String isbn = "1234567890";

        // when
        given(bookService.viewBookDetails(isbn))
                .willThrow(BookNotFoundException.class);

        // then
        mockMvc
                .perform(get("/books/" + isbn))
                .andExpect(status().isNotFound());
    }

}