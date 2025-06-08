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
}