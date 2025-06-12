package com.library.loanMicroservice.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GenreDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testConstructorAndGetter() {
        GenreDto dto = new GenreDto("Ficção Científica");
        assertEquals("Ficção Científica", dto.getName());
    }

    @Test
    void testSetter() {
        GenreDto dto = new GenreDto("Romance");
        dto.setName("Mistério");
        assertEquals("Mistério", dto.getName());
    }

    @Test
    void testNameNotBlank_validValue() {
        GenreDto dto = new GenreDto("Aventura");

        Set<ConstraintViolation<GenreDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Não deve haver violações de validação");
    }

    @Test
    void testNameNotBlank_blankValue() {
        GenreDto dto = new GenreDto("   "); // espaços em branco

        Set<ConstraintViolation<GenreDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Deve haver violação para name em branco");

        ConstraintViolation<GenreDto> violation = violations.iterator().next();
        assertEquals("O nome do gênero é obrigatório", violation.getMessage());
    }

    @Test
    void testNameNotBlank_nullValue() {
        GenreDto dto = new GenreDto(null);

        Set<ConstraintViolation<GenreDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Deve haver violação para name nulo");

        ConstraintViolation<GenreDto> violation = violations.iterator().next();
        assertEquals("O nome do gênero é obrigatório", violation.getMessage());
    }
}