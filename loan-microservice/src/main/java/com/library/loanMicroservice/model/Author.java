package com.library.loanMicroservice.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table (name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "birth_date")
    private OffsetDateTime dataNascimento;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

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

    public OffsetDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(OffsetDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
