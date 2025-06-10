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

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    public BookDTO createBook(BookDTO dto) {
        Author author = authorRepository.findByName(dto.getAuthorName())
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado"));

        Genre genre = genreRepository.findByName(dto.getGenre().getName())
                .orElseThrow(() -> new EntityNotFoundException("Gênero não encontrado"));

        Book book = Book.builder() //eu errando aqui e vc nem pra avisar -- eu errando junto
                .title(dto.getTitle())
                .author(author)
                .genre(genre)
                .year_publication(dto.getYear_publication())
                .build(); // o bug aqui ó, tinha q converter book pra dto, eu tava convertendo errado rsrs
        //nn acredito que era isso kkkk
        //falei q era erro de tipo, da pra transformar com map dps, -- precisa? nao, opcional prft ent

        Book savedBook = bookRepository.save(book);

        return BookDTO.builder()
                .id(savedBook.getId())
                .title(savedBook.getTitle())
                .authorName(savedBook.getAuthor().getName())
                .genre(savedBook.getGenre())
                .year_publication(savedBook.getYear_publication())
                .build();
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

    public String deleteById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado.");
        }
        bookRepository.delete(book.get());
        return "Livro deletado.";
    }
}
