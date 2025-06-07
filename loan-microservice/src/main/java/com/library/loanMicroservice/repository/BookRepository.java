package com.library.loanMicroservice.repository;

import com.library.loanMicroservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
