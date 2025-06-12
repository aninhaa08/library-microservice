package com.library.loanMicroservice.controller;

import com.library.loanMicroservice.dto.BookDTO;
import com.library.loanMicroservice.model.Author;
import com.library.loanMicroservice.model.Book;
import com.library.loanMicroservice.model.Genre;
import com.library.loanMicroservice.repository.AuthorRepository;
import com.library.loanMicroservice.repository.BookRepository;
import com.library.loanMicroservice.repository.GenreRepository;
import com.library.loanMicroservice.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private GenreRepository genreRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        // Instancia controller com service e repo necessários
        bookController = new BookController(bookService, bookRepository);
        // Injetar os repos de Author e Genre que são @Autowired
        ReflectionTestUtils.setField(bookController, "authorRepository", authorRepository);
        ReflectionTestUtils.setField(bookController, "genreRepository", genreRepository);
    }

    @Test
    void getBooksByGenreId_Found() {
        Long genreId = 1L;
        List<Book> books = List.of(new Book(), new Book());
        when(bookService.findByGenreId(genreId)).thenReturn(books);

        ResponseEntity<List<Book>> response = bookController.getBooksByGenreId(genreId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(books, response.getBody());
        verify(bookService).findByGenreId(genreId);
    }

    @Test
    void getBooksByGenreId_NotFound() {
        Long genreId = 999L;
        when(bookService.findByGenreId(genreId)).thenReturn(List.of());

        ResponseEntity<List<Book>> response = bookController.getBooksByGenreId(genreId);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(bookService).findByGenreId(genreId);
    }

    @Test
    void getBook_Found() {
        Long id = 1L;
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(id);

        when(bookService.getBookById(id)).thenReturn(bookDTO);

        ResponseEntity<BookDTO> response = bookController.getBook(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bookDTO, response.getBody());
        verify(bookService).getBookById(id);
    }

    @Test
    void updateBook_Found() {
        Long id = 1L;
        Book existingBook = new Book();
        existingBook.setId(id);

        Author author = new Author();
        author.setId(2L);
        author.setName("New Author");

        Genre genre = new Genre();
        genre.setId(3L);
        genre.setName("New Genre");

        BookDTO dto = new BookDTO();
        dto.setTitle("Updated Title");
        dto.setYear_publication(2023);
        dto.setAuthorId(author.getId());
        dto.setGenreId(genre.getId());

        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));
        when(authorRepository.findById(dto.getAuthorId())).thenReturn(Optional.of(author));
        when(genreRepository.findById(dto.getGenreId())).thenReturn(Optional.of(genre));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<Book> response = bookController.updateBook(id, dto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated Title", response.getBody().getTitle());
        assertEquals(author, response.getBody().getAuthor());
        assertEquals(genre, response.getBody().getGenre());

        verify(bookRepository).findById(id);
        verify(authorRepository).findById(dto.getAuthorId());
        verify(genreRepository).findById(dto.getGenreId());
        verify(bookRepository).save(existingBook);
    }

    @Test
    void updateBook_NotFound() {
        Long id = 999L;
        BookDTO dto = new BookDTO();

        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Book> response = bookController.updateBook(id, dto);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(bookRepository).findById(id);
        verify(bookRepository, never()).save(any());
    }

    @Test
    void deleteBook() {
        Long id = 1L;
        String msg = "Livro deletado com sucesso";

        when(bookService.deleteById(id)).thenReturn(msg);

        ResponseEntity<String> response = bookController.delete(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(msg, response.getBody());
        verify(bookService).deleteById(id);
    }
}
