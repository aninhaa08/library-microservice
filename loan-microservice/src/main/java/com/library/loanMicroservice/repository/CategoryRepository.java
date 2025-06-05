package com.library.loanMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.loanMicroservice.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
