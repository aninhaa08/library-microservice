package com.library.loanMicroservice.service;

import com.library.loanMicroservice.model.Genre;
import com.library.loanMicroservice.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private Genre createGenre(Long id, String name) {
        Genre genre = new Genre();
        genre.setId(id);
        genre.setName(name);
        return genre;
    }

    @Test
    void testDeleteByIdSuccess() {
        Long id = 1L;
        Genre genre = createGenre(id, "Ficção");

        when(genreRepository.findById(id)).thenReturn(Optional.of(genre));
        doNothing().when(genreRepository).delete(genre);

        String result = genreService.deleteById(id);

        assertThat(result).isEqualTo("Gênero excluído.");

        verify(genreRepository, times(1)).findById(id);
        verify(genreRepository, times(1)).delete(genre);
        verifyNoMoreInteractions(genreRepository);
    }

    @Test
    void testDeleteByIdNotFound() {
        Long id = 99L;

        when(genreRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> genreService.deleteById(id))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Gênero não encontrado.");

        verify(genreRepository, times(1)).findById(id);
        verifyNoMoreInteractions(genreRepository);
    }
}
