package com.library.loanMicroservice.service;

import com.library.loanMicroservice.model.Book;
import com.library.loanMicroservice.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
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

    private Book createBook(Long id, String title, String genreName) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        if (book.getGenre() == null) {
            book.setGenre(new com.library.loanMicroservice.model.Genre());
        }
        book.getGenre().setName(genreName);
        return book;
    }

    @Test
    void testFindByGenreName() {
        String genre = "Ficção";
        List<Book> books = Arrays.asList(
                createBook(1L, "Livro 1", genre),
                createBook(2L, "Livro 2", genre)
        );

        when(bookRepository.findByGenre_NameIgnoreCase(genre)).thenReturn(books);

        List<Book> result = bookService.findByGenreName(genre);

        assertThat(result).hasSize(2).containsAll(books);
        verify(bookRepository, times(1)).findByGenre_NameIgnoreCase(genre);
    }

    @Test
    void testGetBookByIdFound() {
        Book book = createBook(1L, "Livro Teste", "Drama");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.getBookById(1L);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testGetBookByIdNotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Book> result = bookService.getBookById(99L);

        assertThat(result).isEmpty();
        verify(bookRepository, times(1)).findById(99L);
    }

    @Test
    void testDeleteByIdSuccess() {
        Long id = 1L;
        Book book = createBook(id, "Livro para deletar", "Ação");
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        String result = bookService.deleteById(id.intValue());

        assertThat(result).isEqualTo("Livro deletado.");
        verify(bookRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteByIdNotFound() {
        Long id = 99L;
        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.deleteById(id.intValue()))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Livro não encontrado.");

        verify(bookRepository, times(1)).findById(id);
    }
}
