package com.library.loanMicroservice.controller;

import com.library.loanMicroservice.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenreControllerTest {

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void delete_ValidId_ReturnsSuccessMessage() {
        Long genreId = 1L;
        String expectedMessage = "Gênero deletado com sucesso";

        when(genreService.deleteById(genreId)).thenReturn(expectedMessage);

        ResponseEntity<String> response = genreController.delete(genreId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody());

        verify(genreService, times(1)).deleteById(genreId);
    }

    @Test
    void delete_NonExistentId_ReturnsErrorMessage() {
        Long invalidId = 999L;
        String errorMessage = "Gênero não encontrado";

        when(genreService.deleteById(invalidId)).thenReturn(errorMessage);

        ResponseEntity<String> response = genreController.delete(invalidId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());

        verify(genreService, times(1)).deleteById(invalidId);
    }
}
