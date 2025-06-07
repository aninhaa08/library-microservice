package com.library.loanMicroservice.service;

import com.library.loanMicroservice.model.Book;
import com.library.loanMicroservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> findByGenreName(String genre) {
        return bookRepository.findByGenre_NameIgnoreCase(genre);
    }
}

