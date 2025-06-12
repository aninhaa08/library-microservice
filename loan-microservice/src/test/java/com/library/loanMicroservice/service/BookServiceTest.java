package com.library.loanMicroservice.service;

import com.library.loanMicroservice.dto.BookDTO;
import com.library.loanMicroservice.model.Author;
import com.library.loanMicroservice.model.Book;
import com.library.loanMicroservice.model.Genre;
import com.library.loanMicroservice.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private Book createBook(Long id, String title, Long genreId, String genreName) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);

        Genre genre = new Genre();
        genre.setId(genreId);
        genre.setName(genreName);
        book.setGenre(genre);

        return book;
    }

    @Test
    void testFindByGenreId() {
        Long genreId = 1L;
        List<Book> books = Arrays.asList(
                createBook(1L, "Livro 1", genreId, "Ficção"),
                createBook(2L, "Livro 2", genreId, "Ficção")
        );

        when(bookRepository.findByGenre_Id(genreId)).thenReturn(books);

        List<Book> result = bookService.findByGenreId(genreId);

        assertThat(result).hasSize(2).containsAll(books);
        verify(bookRepository, times(1)).findByGenre_Id(genreId);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void testGetBookByIdFound() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Livro Teste");
        book.setYear_publication(2020);

        Author author = new Author();
        author.setName("Autor Exemplo");
        author.setId(5L);
        book.setAuthor(author);

        Genre genre = new Genre();
        genre.setId(3L);
        genre.setName("Drama");
        book.setGenre(genre);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookDTO result = bookService.getBookById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Livro Teste");
        assertThat(result.getYear_publication()).isEqualTo(2020);
        assertThat(result.getAuthorName()).isEqualTo("Autor Exemplo");
        assertThat(result.getGenre()).isNotNull();
        assertThat(result.getGenre().getName()).isEqualTo("Drama");

        verify(bookRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void testGetBookByIdNotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.getBookById(99L))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Livro não encontrado.");

        verify(bookRepository, times(1)).findById(99L);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void testDeleteByIdSuccess() {
        Long id = 1L;
        Book book = createBook(id, "Livro para deletar", 2L, "Ação");

        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).delete(book);

        String result = bookService.deleteById(id);

        assertThat(result).isEqualTo("Livro deletado.");

        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).delete(book);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void testDeleteByIdNotFound() {
        Long id = 99L;
        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.deleteById(id))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Livro não encontrado.");

        verify(bookRepository, times(1)).findById(id);
        verifyNoMoreInteractions(bookRepository);
    }
}
