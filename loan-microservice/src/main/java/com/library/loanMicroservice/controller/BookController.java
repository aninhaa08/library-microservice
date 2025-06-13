package com.library.loanMicroservice.controller;

import com.library.loanMicroservice.dto.BookDTO;
import com.library.loanMicroservice.model.Author;
import com.library.loanMicroservice.model.Book;
import com.library.loanMicroservice.model.Genre;
import com.library.loanMicroservice.repository.AuthorRepository;
import com.library.loanMicroservice.repository.BookRepository;
import com.library.loanMicroservice.repository.GenreRepository;
import com.library.loanMicroservice.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Livros", description = "Operações relacionadas aos livros")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookService bookService;
    private final BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @Operation(summary = "Cria um novo livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o livro"),
            @ApiResponse(responseCode = "409", description = "Livro já existente"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @PostMapping (
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookDTO dto) {
        BookDTO createdBook = bookService.createBook(dto);
        return ResponseEntity.status(201).body(createdBook);
    }

    @Operation(summary = "Encontrar livro pelo gênero", description = "Busca um livro existente pelo seu gênero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @GetMapping("/genre/id/{genreId}")
    public ResponseEntity<List<Book>> getBooksByGenreId(@PathVariable Long genreId) {
        List<Book> books = bookService.findByGenreId(genreId);
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Encontrar livro pelo id", description = "Busca um livro existente pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        BookDTO book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @Operation(summary = "Atualizar livro pelo ID", description = "Atualiza um livro existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDTO updatedBookDTO) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();

            book.setTitle(updatedBookDTO.getTitle());
            book.setYear_publication(updatedBookDTO.getYear_publication());

            Author author = authorRepository.findById(updatedBookDTO.getAuthorId())
                    .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado com ID: " + updatedBookDTO.getAuthorId()));
            book.setAuthor(author);

            Genre genre = genreRepository.findById(updatedBookDTO.getGenreId())
                    .orElseThrow(() -> new EntityNotFoundException("Gênero não encontrado com ID: " + updatedBookDTO.getGenreId()));
            book.setGenre(genre);

            bookRepository.save(book);

            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir livro por ID", description = "Exclui um livro existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(bookService.deleteById(id));
    }
}
