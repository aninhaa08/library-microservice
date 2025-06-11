package com.library.loanMicroservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BookTest {

    @Test
    void testConstructorAndGetters() {
        Long id = 1L;
        String title = "Dom Casmurro";
        Integer yearPublication = 1899;

        Author author = new Author(1L, "Machado de Assis", null);
        Genre genre = new Genre();
        genre.setName("Romance");

        Book book = new Book(id, title, yearPublication, author, genre);

        assertEquals(id, book.getId());
        assertEquals(title, book.getTitle());
        assertEquals(yearPublication, book.getYear_publication());
        assertEquals(author, book.getAuthor());
        assertEquals(genre, book.getGenre());
    }

    @Test
    void testSettersAndGetters() {
        Book book = new Book();

        Long id = 2L;
        String title = "Iracema";
        Integer yearPublication = 1865;

        Author author = new Author();
        author.setName("José de Alencar");

        Genre genre = new Genre();
        genre.setName("Romance");

        book.setId(id);
        book.setTitle(title);
        book.setYear_publication(yearPublication);
        book.setAuthor(author);
        book.setGenre(genre);

        assertEquals(id, book.getId());
        assertEquals(title, book.getTitle());
        assertEquals(yearPublication, book.getYear_publication());
        assertEquals(author, book.getAuthor());
        assertEquals(genre, book.getGenre());
    }
}