package com.library.loanMicroservice.controller;

import com.library.loanMicroservice.dto.AuthorDto;
import com.library.loanMicroservice.model.Author;
import com.library.loanMicroservice.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAuthor_Success() {
        AuthorDto dto = new AuthorDto();
        Author author = new Author();
        author.setId(1L);

        when(authorService.createAuthor(dto)).thenReturn(author);

        ResponseEntity<Author> response = authorController.createAuthor(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(author, response.getBody());
        verify(authorService).createAuthor(dto);
    }

    @Test
    void testGetAllAuthors_ReturnsList() {
        List<Author> authors = List.of(new Author(), new Author());
        when(authorService.getAuthor()).thenReturn(authors);

        List<Author> result = authorController.getAllAuthors();

        assertEquals(authors.size(), result.size());
        verify(authorService).getAuthor();
    }

    @Test
    void testGetAuthor_Found() {
        Long authorId = 1L;
        Author author = new Author();
        author.setId(authorId);

        when(authorService.getAuthorById(authorId)).thenReturn(Optional.of(author));

        ResponseEntity<Author> response = authorController.getAuthor(authorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(author, response.getBody());
        verify(authorService).getAuthorById(authorId);
    }

    @Test
    void testGetAuthor_NotFound() {
        Long authorId = 999L;

        when(authorService.getAuthorById(authorId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            authorController.getAuthor(authorId);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getReason().contains("Autor não encontrado"));
        verify(authorService).getAuthorById(authorId);
    }

    @Test
    void testUpdateAuthor_Success() {
        Long authorId = 1L;
        AuthorDto dto = new AuthorDto();
        Author updatedAuthor = new Author();
        updatedAuthor.setId(authorId);

        when(authorService.updateAuthor(authorId, dto)).thenReturn(updatedAuthor);

        ResponseEntity<Author> response = authorController.updateAuthor(authorId, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedAuthor, response.getBody());
        verify(authorService).updateAuthor(authorId, dto);
    }

    @Test
    void testDeleteAuthor_Success() {
        Long authorId = 1L;
        String expectedMessage = "Autor deletado com sucesso";

        when(authorService.deleteById(authorId)).thenReturn(expectedMessage);

        ResponseEntity<String> response = authorController.delete(authorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody());
        verify(authorService).deleteById(authorId);
    }
}
