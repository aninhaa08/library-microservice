package com.library.loanMicroservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table (name = "authors")
@Schema(description = "Representa um autor")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do autor", example = "1L")
    private Long id;

    @Schema(description = "Nome do autor", example = "Machado de Assis")
    private String name;

    @Column(name = "birth_date")
    @Schema(description = "Data de nascimento do autor", example = "OffsetDateTime.parse(\"1839-06-21T00:00:00-03:00\")")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "author")
    @Schema(description = "Lista de livros escritos pelo autor", example = "[{\"title\": \"Dom Casmurro\"}]")
    private List<Book> books;

    public Author(Long id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Author() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public OffsetDateTime getDataNascimento(OffsetDateTime dataNascimento) {
        return dataNascimento;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


}
