package com.library.loanMicroservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class GenreTest {

    @Test
    void testConstructorAndGetters() {
        Long id = 1L;
        String name = "Romance";

        Genre genre = new Genre(id, name);

        assertEquals(id, genre.getId());
        assertEquals(name, genre.getName());
    }

    @Test
    void testSettersAndGetters() {
        Genre genre = new Genre();

        Long id = 2L;
        String name = "Ficção Científica";

        genre.setId(id);
        genre.setName(name);

        assertEquals(id, genre.getId());
        assertEquals(name, genre.getName());
    }

    @Test
    void testBooksGetterAndSetter() {
        Genre genre = new Genre();
        Book book1 = new Book();
        book1.setTitle("Duna");
        Book book2 = new Book();
        book2.setTitle("Neuromancer");

        List<Book> books = Arrays.asList(book1, book2);
        genre.setBooks(books);

        assertEquals(2, genre.getBooks().size());
        assertEquals("Duna", genre.getBooks().get(0).getTitle());
        assertEquals("Neuromancer", genre.getBooks().get(1).getTitle());
    }
}