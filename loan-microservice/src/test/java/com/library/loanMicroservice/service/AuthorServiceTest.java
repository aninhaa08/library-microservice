package com.library.loanMicroservice.service;

import com.library.loanMicroservice.dto.AuthorDto;
import com.library.loanMicroservice.model.Author;
import com.library.loanMicroservice.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private AuthorDto createAuthorDto(String name, OffsetDateTime birthDate) {
        AuthorDto dto = new AuthorDto();
        dto.setName(name);
        // Converter OffsetDateTime para LocalDate para o AuthorDto
        dto.setBirthDate(birthDate.toLocalDate());
        return dto;
    }

    private Author createAuthor(Long id, String name, LocalDate birthDate) {
        return new Author(id, name, birthDate);
    }

    @Test
    void testCreateAuthor() {
        AuthorDto dto = createAuthorDto("Autor Teste", OffsetDateTime.parse("1980-01-01T00:00:00Z"));
        Author savedAuthor = createAuthor(1L, "Autor Teste", dto.getBirthDate());

        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);

        Author result = authorService.createAuthor(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo(dto.getName());
        assertThat(result.getBirthDate()).isEqualTo(dto.getBirthDate());

        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void testGetAuthor() {
        List<Author> authors = Arrays.asList(
                createAuthor(1L, "Autor 1", LocalDate.of(1980, 1, 1)),
                createAuthor(2L, "Autor 2", LocalDate.of(1990, 5, 5))
        );

        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = authorService.getAuthor();

        assertThat(result).hasSize(2).containsAll(authors);

        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void testGetAuthorByIdFound() {
        Author author = createAuthor(1L, "Autor Teste", LocalDate.of(1980, 1, 1));
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Optional<Author> result = authorService.getAuthorById(1L);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAuthorByIdNotFound() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Author> result = authorService.getAuthorById(99L);

        assertThat(result).isEmpty();

        verify(authorRepository, times(1)).findById(99L);
    }

    @Test
    void testUpdateAuthorSuccess() {
        Long id = 1L;
        Author existingAuthor = createAuthor(id, "Old Name", LocalDate.of(1970, 1, 1));
        AuthorDto dto = createAuthorDto("New Name", OffsetDateTime.parse("1985-03-03T00:00:00Z"));
        Author updatedAuthor = createAuthor(id, dto.getName(), dto.getBirthDate());

        when(authorRepository.findById(id)).thenReturn(Optional.of(existingAuthor));
        when(authorRepository.save(existingAuthor)).thenReturn(updatedAuthor);

        Author result = authorService.updateAuthor(id, dto);

        assertThat(result.getName()).isEqualTo(dto.getName());
        assertThat(result.getBirthDate()).isEqualTo(dto.getBirthDate());

        verify(authorRepository, times(1)).findById(id);
        verify(authorRepository, times(1)).save(existingAuthor);
    }

    @Test
    void testUpdateAuthorNotFound() {
        Long id = 99L;
        AuthorDto dto = createAuthorDto("Name", OffsetDateTime.now());

        when(authorRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorService.updateAuthor(id, dto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Autor de ID: " + id + " não encontrado.");

        verify(authorRepository, times(1)).findById(id);
        verify(authorRepository, never()).save(any());
    }

    @Test
    void testDeleteByIdSuccess() {
        Long id = 1L;
        Author author = createAuthor(id, "Autor Teste", LocalDate.of(1980, 1, 1));

        when(authorRepository.findById(id)).thenReturn(Optional.of(author));
        doNothing().when(authorRepository).delete(author);

        String result = authorService.deleteById(id);

        assertThat(result).isEqualTo("Autor deletado.");

        verify(authorRepository, times(1)).findById(id);
        verify(authorRepository, times(1)).delete(author);
    }

    @Test
    void testDeleteByIdNotFound() {
        Long id = 99L;

        when(authorRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorService.deleteById(id))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Autor não encontrado.");

        verify(authorRepository, times(1)).findById(id);
        verify(authorRepository, never()).delete(any());
    }
}
