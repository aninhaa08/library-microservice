package com.library.loanMicroservice.service;

import java.util.Optional;

import com.library.loanMicroservice.dto.AuthorDto;
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

    public Author createAuthor(AuthorDto dto){
        Author author = new Author();

        author.setName(dto.getName());
        author.setBirthDate(dto.getBirthDate());
        return authorRepository.save(author);
    }

    public Optional<Author> getAuthorById(Long id) {
        return this.authorRepository.findById(id);
    }

    public String deleteById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado.");
        }
        return "Autor deletado.";
    }
}
