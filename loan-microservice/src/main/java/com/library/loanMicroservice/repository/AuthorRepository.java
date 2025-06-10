package com.library.loanMicroservice.repository;

import com.library.loanMicroservice.model.Author;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Override
    @NonNull
    Optional<Author> findById(@NonNull Long id);

    Optional<Author> findByName(String name);
}
