package com.library.loanMicroservice.repository;

import com.library.loanMicroservice.model.Author;
import com.library.loanMicroservice.model.Book;
import com.library.loanMicroservice.model.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("Deve criar um livro com autor e gênero")
    void shouldCreateBook() {
        Author author = authorRepository.save(new Author(null, "Machado de Assis", LocalDate.of(1839, 6, 21)));
        Genre genre = genreRepository.save(new Genre(null, "Romance"));

        Book book = new Book();
        book.setTitle("Dom Casmurro");
        book.setYear_publication(1899);
        book.setAuthor(author);
        book.setGenre(genre);

        Book savedBook = bookRepository.save(book);

        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("Dom Casmurro");
        assertThat(savedBook.getAuthor().getName()).isEqualTo("Machado de Assis");
        assertThat(savedBook.getGenre().getName()).isEqualTo("Romance");
    }

    @Test
    @DisplayName("Deve salvar um livro com autor e gênero")
    void shouldSaveBook() {
        Author author = authorRepository.save(new Author(null, "Machado de Assis", LocalDate.of(1839, 6, 21)));
        Genre genre = genreRepository.save(new Genre(null, "Romance"));

        Book book = new Book();
        book.setTitle("Dom Casmurro");
        book.setYear_publication(1899);
        book.setAuthor(author);
        book.setGenre(genre);

        Book saved = bookRepository.save(book);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("Dom Casmurro");
        assertThat(saved.getAuthor().getName()).isEqualTo("Machado de Assis");
        assertThat(saved.getGenre().getName()).isEqualTo("Romance");
    }

    @Test
    @DisplayName("Deve buscar um livro por ID")
    void shouldFindById() {
        Author author = authorRepository.save(new Author(null, "Clarice Lispector", LocalDate.of(1920, 12, 10)));
        Genre genre = genreRepository.save(new Genre(null, "Literatura Brasileira"));

        Book book = new Book();
        book.setTitle("A Hora da Estrela");
        book.setYear_publication(1977);
        book.setAuthor(author);
        book.setGenre(genre);

        Book saved = bookRepository.save(book);

        Optional<Book> found = bookRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("A Hora da Estrela");
    }

    @Test
    @DisplayName("Deve deletar um livro")
    void shouldDeleteBook() {
        Author author = authorRepository.save(new Author(null, "Autor Teste", LocalDate.of(1980, 1, 1)));
        Genre genre = genreRepository.save(new Genre(null, "Ficção"));

        Book book = new Book();
        book.setTitle("Livro Teste");
        book.setYear_publication(2000);
        book.setAuthor(author);
        book.setGenre(genre);

        Book saved = bookRepository.save(book);
        Long id = saved.getId();

        bookRepository.delete(saved);

        assertThat(bookRepository.findById(id)).isNotPresent();
    }

    @Test
    @DisplayName("Deve buscar livros filtrando pelo título em memória")
    void shouldFindBooksFilteringByTitleInMemory() {
        Author author = authorRepository.save(new Author(null, "Autor Teste", LocalDate.of(1970, 1, 1)));
        Genre genre = genreRepository.save(new Genre(null, "Gênero Teste"));

        bookRepository.save(new Book(null, "Java Básico", 2010, author, genre));
        bookRepository.save(new Book(null, "Aprenda Java", 2015, author, genre));
        bookRepository.save(new Book(null, "Spring Framework", 2020, author, genre));

        List<Book> allBooks = bookRepository.findAll();

        List<Book> filtered = allBooks.stream()
                .filter(book -> book.getTitle() != null && book.getTitle().toLowerCase().contains("java"))
                .collect(Collectors.toList());

        assertEquals(2, filtered.size());
    }

}
