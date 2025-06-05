package com.library.loanMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.loanMicroservice.model.Books;

public interface BooksRepository extends JpaRepository<Books, Integer> {
    
}
