package com.library.loanMicroservice.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorId")
    @JsonIgnoreProperties("books")
    private Authors author;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    @JsonIgnoreProperties("books")
    private Category categories;

    @Column(nullable = true)
    private String preface;

    @Column(name = "publication_year", nullable = false)
    private LocalDateTime publicationYear;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Authors author) {
        this.author = author;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

    public void setPreface(String preface) {
        this.preface = preface;
    }

    @PrePersist
    public void setPublicationYear() {
        this.publicationYear = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Authors getAuthor() {
        return author;
    }

    public Category getCategories() {
        return categories;
    }

    public String getPreface() {
        return preface;
    }

    public LocalDateTime getPublicationYear() {
        return publicationYear;
    }
    
}
