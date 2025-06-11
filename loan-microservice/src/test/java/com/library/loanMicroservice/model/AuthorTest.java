package com.library.loanMicroservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AuthorTest {

    @Test
    void testConstructorAndGetters() {
        Long id = 1L;
        String name = "Machado de Assis";
        LocalDate birthDate = LocalDate.of(1839, 6, 21);

        Author author = new Author(id, name, birthDate);

        assertEquals(id, author.getId());
        assertEquals(name, author.getName());
        assertEquals(birthDate, author.getBirthDate());
    }

    @Test
    void testSettersAndGetters() {
        Author author = new Author();

        Long id = 2L;
        String name = "José de Alencar";
        LocalDate birthDate = LocalDate.of(1829, 5, 1);

        author.setId(id);
        author.setName(name);
        author.setBirthDate(birthDate);

        assertEquals(id, author.getId());
        assertEquals(name, author.getName());
        assertEquals(birthDate, author.getBirthDate());
    }

    @Test
    void testBooksGetterAndSetter() {
        Author author = new Author();
        Book book1 = new Book();
        book1.setTitle("Iracema");
        Book book2 = new Book();
        book2.setTitle("Senhora");

        List<Book> books = Arrays.asList(book1, book2);
        author.setBooks(books);

        assertEquals(2, author.getBooks().size());
        assertEquals("Iracema", author.getBooks().get(0).getTitle());
        assertEquals("Senhora", author.getBooks().get(1).getTitle());
    }

    @Test
    void testGetDataNascimento() {
        Author author = new Author();

        java.time.OffsetDateTime data = java.time.OffsetDateTime.parse("1839-06-21T00:00:00-03:00");
        assertEquals(data, author.getDataNascimento(data));
    }
}
