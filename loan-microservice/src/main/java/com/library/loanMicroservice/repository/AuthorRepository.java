package com.library.loanMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.loanMicroservice.model.Authors;

public interface AuthorRepository extends JpaRepository<Authors, Integer> {
}
