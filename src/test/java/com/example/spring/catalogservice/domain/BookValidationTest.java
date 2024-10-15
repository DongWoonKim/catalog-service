package com.example.spring.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BookValidationTest {

    private static Validator validator;
    private static ValidatorFactory factory;

    @BeforeAll
    static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void tearDown() {
        if (factory != null) {
            factory.close(); // ValidatorFactory 자원 해제
        }
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        // given
        Book build = Book.builder()
                .isbn("1234567890")
                .title("Title")
                .author("Author")
                .price(9.9)
                .build();

        // when
        Set<ConstraintViolation<Book>> violations = validator.validate(build);

        // then
        assertThat(violations).isEmpty();
    }

    @Test
    void whenAllFieldsIncorrectThenValidationFails() {
        // given
        Book build = Book.builder()
                .isbn("123456789a")
                .title("Title")
                .author("Author")
                .price(9.9)
                .build();

        // when
        Set<ConstraintViolation<Book>> violations = validator.validate(build);

        // then
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The ISBN format must be valid.");
    }

}