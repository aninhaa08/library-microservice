package com.library.loanMicroservice.repository;

import com.library.loanMicroservice.model.Genre;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Override
    @NonNull
    Optional<Genre> findById(@NonNull Long id);

    Optional<Genre> findByName(String name);
}
