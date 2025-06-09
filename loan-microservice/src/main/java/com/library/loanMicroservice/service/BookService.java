package com.library.loanMicroservice.service;

import com.library.loanMicroservice.model.Book;
import com.library.loanMicroservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> findByGenreName(String genre) {
        return bookRepository.findByGenre_NameIgnoreCase(genre);
    }

    public Optional<Book> getBookById(Long id) {
        return this.bookRepository.findById(id);
    }

    public String deleteById(Integer id) {
        Optional<Book> book = bookRepository.findById(Long.valueOf(id));
        if (!book.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado.");
        }
        bookRepository.delete(book.get());
        return "Livro deletado.";
    }
}
