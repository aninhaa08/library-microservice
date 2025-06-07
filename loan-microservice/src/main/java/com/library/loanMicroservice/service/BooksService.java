package com.library.loanMicroservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.library.loanMicroservice.model.Books;
import com.library.loanMicroservice.repository.BooksRepository;

@Service
public class BooksService {
    
    @Autowired
    private BooksRepository booksRepository;

    public String deleteById(Integer id) {
        Optional<Books> book = booksRepository.findById(id);
        if (!book.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado.");
        }
        return "Livro deletado.";
    }
}
