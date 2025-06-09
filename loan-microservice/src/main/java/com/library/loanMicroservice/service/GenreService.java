package com.library.loanMicroservice.service;

import java.util.List;
import java.util.Optional;

import com.library.loanMicroservice.model.Book;
import com.library.loanMicroservice.model.Genre;
import com.library.loanMicroservice.repository.BookRepository;
import com.library.loanMicroservice.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    public void deleteById(Long idExcluido) {
        List<Book> books = bookRepository.findByGenreId(idExcluido);
        for (Book book : books) {
            book.setGenre(null);
        }
        bookRepository.saveAll(books);
        genreRepository.deleteById(idExcluido);
    }
}
