package com.library.loanMicroservice.service;

import java.util.List;
import java.util.Optional;

import com.library.loanMicroservice.dto.AuthorDto;
import com.library.loanMicroservice.model.Author;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.library.loanMicroservice.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author createAuthor(AuthorDto dto){
        Author author = new Author(null, null, null);

        author.setName(dto.getName());
        author.setBirthDate(dto.getBirthDate());
        return authorRepository.save(author);
    }

    public List<Author> getAuthor() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return this.authorRepository.findById(id);
    }

    public Author updateAuthor(Long id, AuthorDto dto){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor de ID: " + id + " não encontrado."));

        author.setName(dto.getName());
        author.setBirthDate(dto.getBirthDate());

        return authorRepository.save(author);
    }

    public String deleteById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado.");
        }
        authorRepository.delete(author.get());
        return "Autor deletado.";
    }
}