package com.library.loanMicroservice.repository;

import com.library.loanMicroservice.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
