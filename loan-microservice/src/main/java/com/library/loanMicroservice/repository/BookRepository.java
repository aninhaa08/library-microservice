package com.library.loanMicroservice.repository;

import com.library.loanMicroservice.model.Book;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByGenre_NameIgnoreCase(String name);

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  
    @Override
    @NonNull
    Optional<Book> findById(@NonNull Long id);

}
