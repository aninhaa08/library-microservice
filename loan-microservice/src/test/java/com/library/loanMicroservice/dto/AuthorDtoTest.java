package com.library.loanMicroservice.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AuthorDtoTest {

    private static Validator validator;

    @BeforeAll
    public static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testGettersAndSetters() {
        AuthorDto author = new AuthorDto();

        String name = "John Doe";
        OffsetDateTime birthDate = OffsetDateTime.parse("1990-01-01T00:00:00+00:00");

        author.setName(name);
        author.setBirthDate(birthDate);

        assertThat(author.getName()).isEqualTo(name);
        assertThat(author.getBirthDate()).isEqualTo(birthDate);
    }

    @Test
    public void testNameNotBlankValidation_whenNameIsBlank() {
        AuthorDto author = new AuthorDto();
        author.setBirthDate(OffsetDateTime.now());

        Set<ConstraintViolation<AuthorDto>> violations = validator.validate(author);

        assertThat(violations)
                .isNotEmpty()
                .anyMatch(v -> v.getPropertyPath().toString().equals("name"));
    }

    @Test
    public void testNameNotBlankValidation_whenNameIsNull() {
        AuthorDto author = new AuthorDto();
        author.setName(null);
        author.setBirthDate(OffsetDateTime.now());

        Set<ConstraintViolation<AuthorDto>> violations = validator.validate(author);

        assertThat(violations)
                .isNotEmpty()
                .anyMatch(v -> v.getPropertyPath().toString().equals("name"));
    }

    @Test
    public void testNameNotBlankValidation_whenNameIsValid() {
        AuthorDto author = new AuthorDto();
        author.setName("Jane Doe");
        author.setBirthDate(OffsetDateTime.now());

        Set<ConstraintViolation<AuthorDto>> violations = validator.validate(author);

        assertThat(violations).isEmpty();
    }
}
