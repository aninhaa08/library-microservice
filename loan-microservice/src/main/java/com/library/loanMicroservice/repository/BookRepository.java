package com.library.loanMicroservice.repository;

import com.library.loanMicroservice.model.Book;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @NonNull
    Optional<Book> findById(@NonNull Long id);
}
