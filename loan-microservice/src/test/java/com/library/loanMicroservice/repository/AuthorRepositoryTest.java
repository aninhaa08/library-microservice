package com.library.loanMicroservice.repository;

import com.library.loanMicroservice.model.Author;
import com.library.loanMicroservice.repository.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("Deve salvar um autor no banco de dados")
    void shouldSaveAuthor() {
        Author author = new Author(null, "João da Silva", OffsetDateTime.parse("2000-05-10T00:00:00-03:00").toLocalDate());
        author.setName("João da Silva");
        author.setBirthDate(OffsetDateTime.of(2000, 5, 10, 0, 0, 0, 0, ZoneOffset.of("-03:00")).toLocalDate());

        Author savedAuthor = authorRepository.save(author);

        assertThat(savedAuthor).isNotNull();
        assertThat(savedAuthor.getId()).isNotNull();
        assertThat(savedAuthor.getName()).isEqualTo("João da Silva");
    }

    @Test
    @DisplayName("Deve buscar um autor por ID")
    void shouldFindAuthorById() {
        Author author = new Author(null, "Maria Oliveira", OffsetDateTime.parse("1985-04-22T00:00:00-03:00").toLocalDate());
        Author savedAuthor = authorRepository.save(author);

        var foundAuthor = authorRepository.findById(savedAuthor.getId());

        assertThat(foundAuthor).isPresent();
        assertThat(foundAuthor.get().getName()).isEqualTo("Maria Oliveira");
    }

    @Test
    @DisplayName("Deve retornar todos os autores cadastrados")
    void shouldReturnAllAuthors() {
        Author a1 = authorRepository.save(new Author(null, "Autor 1", OffsetDateTime.parse("1970-01-01T00:00:00-03:00").toLocalDate()));
        Author a2 = authorRepository.save(new Author(null, "Autor 2", OffsetDateTime.parse("1980-01-01T00:00:00-03:00").toLocalDate()));

        var authors = authorRepository.findAll();

        assertThat(authors).hasSize(2).extracting(Author::getName).containsExactlyInAnyOrder("Autor 1", "Autor 2");
    }

    @Test
    @DisplayName("Deve deletar um autor")
    void shouldDeleteAuthor() {
        Author author = authorRepository.save(new Author(null, "Autor para deletar", OffsetDateTime.parse("1990-01-01T00:00:00-03:00").toLocalDate()));
        Long id = author.getId();

        authorRepository.delete(author);

        assertThat(authorRepository.findById(id)).isNotPresent();
    }
}