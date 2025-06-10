package com.library.loanMicroservice.service;

import com.library.loanMicroservice.dto.BookDTO;
import com.library.loanMicroservice.model.Author;
import com.library.loanMicroservice.model.Book;
import com.library.loanMicroservice.model.Genre;
import com.library.loanMicroservice.repository.AuthorRepository;
import com.library.loanMicroservice.repository.BookRepository;
import com.library.loanMicroservice.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Builder
@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    private final GenreRepository genreRepository;

    public Book createBook(BookDTO dto) {
        Author author = authorRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado"));

        Genre genre = genreRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Gênero não encontrado"));

        return bookRepository.save(
                Book.builder()
                        .title(dto.getTitle())
                        .author(author)
                        .genre(genre)
                        .yearPublication(dto.getYear_publication())
                        .build()
        );
    }

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    public List<Book> findByGenreName(String genre) {
        return bookRepository.findByGenre_NameIgnoreCase(genre);
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado."));

        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .year_publication(book.getYear_publication())
                .authorName(book.getAuthor().getName())
                .genre(book.getGenre())
                .build();
    }

    public String deleteById(Integer id) {
        Optional<Book> book = bookRepository.findById(Long.valueOf(id));
        if (!book.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado.");
        }
        return "Livro deletado.";
    }
}
