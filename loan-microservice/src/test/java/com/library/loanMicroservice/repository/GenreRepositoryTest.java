package com.library.loanMicroservice.repository;

import com.library.loanMicroservice.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void saveAndFindById() {
        Genre genre = new Genre();
        genre.setName("Fantasia");

        Genre savedGenre = genreRepository.save(genre);
        Optional<Genre> foundGenre = genreRepository.findById(savedGenre.getId());

        assertTrue(foundGenre.isPresent());
        assertEquals("Fantasia", foundGenre.get().getName());
    }

    @Test
    void deleteGenre() {
        Genre genre = new Genre();
        genre.setName("Terror");
        Genre savedGenre = genreRepository.save(genre);

        genreRepository.deleteById(savedGenre.getId());
        Optional<Genre> deletedGenre = genreRepository.findById(savedGenre.getId());

        assertFalse(deletedGenre.isPresent());
    }
}
