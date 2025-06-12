package com.library.loanMicroservice.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
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
        LocalDate birthDate = LocalDate.of(1990, 1, 1);

        author.setName(name);
        author.setBirthDate(birthDate);

        assertThat(author.getName()).isEqualTo(name);
        assertThat(author.getBirthDate()).isEqualTo(birthDate);
    }

    @Test
    public void testNameNotBlankValidation_whenNameIsBlank() {
        AuthorDto author = new AuthorDto();
        author.setName(""); // valor em branco
        author.setBirthDate(LocalDate.now());

        Set<ConstraintViolation<AuthorDto>> violations = validator.validate(author);

        assertThat(violations)
                .isNotEmpty()
                .anyMatch(v -> v.getPropertyPath().toString().equals("name"));
    }

    @Test
    public void testNameNotBlankValidation_whenNameIsNull() {
        AuthorDto author = new AuthorDto();
        author.setName(null);
        author.setBirthDate(LocalDate.now());

        Set<ConstraintViolation<AuthorDto>> violations = validator.validate(author);

        assertThat(violations)
                .isNotEmpty()
                .anyMatch(v -> v.getPropertyPath().toString().equals("name"));
    }

    @Test
    public void testNameNotBlankValidation_whenNameIsValid() {
        AuthorDto author = new AuthorDto();
        author.setName("Jane Doe");
        author.setBirthDate(LocalDate.now());

        Set<ConstraintViolation<AuthorDto>> violations = validator.validate(author);

        assertThat(violations).isEmpty();
    }

    @Test
    public void testBirthDateNotNullValidation_whenBirthDateIsNull() {
        AuthorDto author = new AuthorDto();
        author.setName("Valid Name");
        author.setBirthDate(null);

        Set<ConstraintViolation<AuthorDto>> violations = validator.validate(author);

        assertThat(violations)
                .isNotEmpty()
                .anyMatch(v -> v.getPropertyPath().toString().equals("birthDate"));
    }
}
