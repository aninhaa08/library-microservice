package com.library.loanMicroservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table (name = "books")
@Schema(description = "Representa um livro")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do livro", example = "1")
    private Long id;

    @Schema(description = "Título do livro", example = "Dom Casmurro")
    private String title;

    @Column(name = "year_publication")
    @Schema(description = "Ano de publicação do livro", example = "1899")
    private Integer year_publication;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @Schema(description = "Nome do autor do livro", example = "Machado de Assis")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @Schema(description = "Gênero do livro", example = "Romance")
    private Genre genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear_publication() {
        return year_publication;
    }

    public void setYear_publication(Integer year_publication) {
        this.year_publication = year_publication;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
