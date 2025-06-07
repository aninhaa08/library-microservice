package com.library.loanMicroservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.library.loanMicroservice.model.Category;
import com.library.loanMicroservice.repository.CategoryRepository;

@Service
public class CategoryService {
    

    @Autowired
    private CategoryRepository categoryRepository;

    public String deleteById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada.");
        }
        return "Categoria deletada.";
    }
}
