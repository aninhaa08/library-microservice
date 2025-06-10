package com.library.loanMicroservice.service;

import java.util.List;
import java.util.Optional;

import com.library.loanMicroservice.dto.GenreDto;
import com.library.loanMicroservice.model.Genre;
import com.library.loanMicroservice.repository.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    public Genre getById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gênero não encontrado."));
    }

    public Genre create(GenreDto dto){
        Genre genre = new Genre();

        genre.setName(dto.getName());
        return genreRepository.save(genre);
    }

    public Genre update(Long id, GenreDto dto) {
        Genre existingGenre = genreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gênero não encontrado."));

        existingGenre.setName(dto.getName());
        return genreRepository.save(existingGenre);
    }

    public String deleteById(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (!genre.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gênero não encontrado.");
        }
        genreRepository.delete(genre.get());
        return "Gênero excluído.";
    }

}
