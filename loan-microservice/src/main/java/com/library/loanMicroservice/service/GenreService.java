package com.library.loanMicroservice.service;

import java.util.Optional;

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

    public String deleteById(Integer id) {
        Optional<Genre> genre = genreRepository.findById(Long.valueOf(id));
        if (!genre.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gênero não encontrado.");
        }
        return "Gênero excluído.";
    }
}
