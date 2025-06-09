package com.library.loanMicroservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "genres")
@Schema(description = "Representa um gênero")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do gênero", example = "1")
    private Long id;

    @Schema(description = "Nome do gênero", example = "Romance")
    private String name;

    @OneToMany(mappedBy = "genre")
    private List<Book> books;

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre() {}

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

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
}
