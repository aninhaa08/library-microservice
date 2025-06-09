package com.library.loanMicroservice.service;

import java.util.List;
import java.util.Optional;

import com.library.loanMicroservice.model.Genre;
import com.library.loanMicroservice.repository.GenreRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
