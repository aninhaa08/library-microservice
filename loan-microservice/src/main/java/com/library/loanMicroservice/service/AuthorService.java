package com.library.loanMicroservice.service;

import java.util.Optional;

import com.library.loanMicroservice.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.library.loanMicroservice.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public String deleteById(Integer id) {
        Optional<Author> author = authorRepository.findById(Long.valueOf(id));
        if (!author.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado.");
        }
        return "Autor deletado.";
    }
}
