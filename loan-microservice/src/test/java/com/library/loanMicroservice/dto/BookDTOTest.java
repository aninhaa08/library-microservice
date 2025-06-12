package com.library.loanMicroservice.dto;

import com.library.loanMicroservice.model.Genre;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDTOTest {

    private Genre createGenre() {
        return new Genre(1L, "Tecnologia");
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        Genre genre = createGenre();

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Effective Java");
        bookDTO.setYear_publication(2008);
        bookDTO.setAuthorName("Joshua Bloch");
        bookDTO.setGenre(genre);
        bookDTO.setAuthorId(10L);
        bookDTO.setGenreId(100L);

        assertEquals(1L, bookDTO.getId());
        assertEquals("Effective Java", bookDTO.getTitle());
        assertEquals(2008, bookDTO.getYear_publication());
        assertEquals("Joshua Bloch", bookDTO.getAuthorName());
        assertEquals(genre, bookDTO.getGenre());
        assertEquals(10L, bookDTO.getAuthorId());
        assertEquals(100L, bookDTO.getGenreId());
    }

    @Test
    void testAllArgsConstructor() {
        Genre genre = new Genre(2L, "Ficção");

        BookDTO bookDTO = new BookDTO(1L, "Clean Code", 2008, "Robert C. Martin",
                genre, 11L, 101L);

        assertEquals(1L, bookDTO.getId());
        assertEquals("Clean Code", bookDTO.getTitle());
        assertEquals(2008, bookDTO.getYear_publication());
        assertEquals("Robert C. Martin", bookDTO.getAuthorName());
        assertEquals(genre, bookDTO.getGenre());
        assertEquals(11L, bookDTO.getAuthorId());
        assertEquals(101L, bookDTO.getGenreId());
    }

    @Test
    void testBuilder() {
        Genre genre = new Genre(3L, "Fantasia");

        BookDTO bookDTO = BookDTO.builder()
                .id(2L)
                .title("Refactoring")
                .year_publication(1999)
                .authorName("Martin Fowler")
                .genre(genre)
                .authorId(12L)
                .genreId(102L)
                .build();

        assertEquals(2L, bookDTO.getId());
        assertEquals("Refactoring", bookDTO.getTitle());
        assertEquals(1999, bookDTO.getYear_publication());
        assertEquals("Martin Fowler", bookDTO.getAuthorName());
        assertEquals(genre, bookDTO.getGenre());
        assertEquals(12L, bookDTO.getAuthorId());
        assertEquals(102L, bookDTO.getGenreId());
    }

    @Test
    void testEqualsAndHashCode() {
        Genre genre = new Genre(4L, "Drama");

        BookDTO book1 = BookDTO.builder()
                .id(3L)
                .title("Domain-Driven Design")
                .year_publication(2003)
                .authorName("Eric Evans")
                .genre(genre)
                .authorId(13L)
                .genreId(103L)
                .build();

        BookDTO book2 = BookDTO.builder()
                .id(3L)
                .title("Domain-Driven Design")
                .year_publication(2003)
                .authorName("Eric Evans")
                .genre(genre)
                .authorId(13L)
                .genreId(103L)
                .build();

        assertEquals(book1, book2);
        assertEquals(book1.hashCode(), book2.hashCode());
    }
}