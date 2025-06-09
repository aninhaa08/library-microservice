package com.library.loanMicroservice.controller;

import com.library.loanMicroservice.model.Author;
import com.library.loanMicroservice.model.Book;
import com.library.loanMicroservice.repository.BookRepository;
import com.library.loanMicroservice.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBooksByGenre_Found() {
        String genre = "Fiction";
        List<Book> books = List.of(new Book(), new Book());
        when(bookService.findByGenreName(genre)).thenReturn(books);

        ResponseEntity<List<Book>> response = bookController.getBooksByGenre(genre);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(books, response.getBody());
        verify(bookService).findByGenreName(genre);
    }

    @Test
    void getBooksByGenre_NotFound() {
        String genre = "UnknownGenre";
        when(bookService.findByGenreName(genre)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Book>> response = bookController.getBooksByGenre(genre);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(bookService).findByGenreName(genre);
    }

    @Test
    void getAuthor_Found() {
        Long id = 1L;
        Book book = new Book();
        book.setId(id);

        when(bookService.getBookById(id)).thenReturn(Optional.of(book));

        ResponseEntity<Book> response = bookController.getAuthor(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(book, response.getBody());
        verify(bookService).getBookById(id);
    }

    @Test
    void getAuthor_NotFound() {
        Long id = 999L;
        when(bookService.getBookById(id)).thenReturn(Optional.empty());

        ResponseEntity<Book> response = bookController.getAuthor(id);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(bookService).getBookById(id);
    }

    @Test
    void updateBook_Found() {
        Long id = 1L;
        Book existingBook = new Book();
        existingBook.setId(id);
        existingBook.setTitle("Old Title");

        Author newAuthor = new Author();
        newAuthor.setId(2L);
        newAuthor.setName("New Author");

        Book updatedBook = new Book();
        updatedBook.setTitle("New Title");
        updatedBook.setAuthor(newAuthor);

        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<Book> response = bookController.updateBook(id, updatedBook);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("New Title", response.getBody().getTitle());
        assertEquals(newAuthor, response.getBody().getAuthor());
        verify(bookRepository).findById(id);
        verify(bookRepository).save(existingBook);
    }

    @Test
    void updateBook_NotFound() {
        Long id = 999L;
        Book updatedBook = new Book();
        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Book> response = bookController.updateBook(id, updatedBook);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(bookRepository).findById(id);
        verify(bookRepository, never()).save(any());
    }

    @Test
    void delete() {
        Integer id = 1;
        String msg = "Livro deletado com sucesso";
        when(bookService.deleteById(id)).thenReturn(msg);

        ResponseEntity<String> response = bookController.delete(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(msg, response.getBody());
        verify(bookService).deleteById(id);
    }
}