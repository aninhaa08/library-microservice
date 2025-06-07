package com.library.loanMicroservice.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    @JsonIgnoreProperties("author")
    private List<Books> books;

    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    @PrePersist
    private void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Books> getBooks() {
        return books;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
