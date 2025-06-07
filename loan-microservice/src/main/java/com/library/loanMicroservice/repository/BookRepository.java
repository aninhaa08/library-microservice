package com.library.loanMicroservice.repository;

import com.library.loanMicroservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByGenre_NameIgnoreCase(String name);
}
