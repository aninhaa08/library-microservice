package com.library.loanMicroservice.controller;

import com.library.loanMicroservice.dto.AuthorDto;
import com.library.loanMicroservice.model.Author;
import com.library.loanMicroservice.repository.AuthorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.loanMicroservice.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody @Valid AuthorDto dto){
        Author saved = this.authorService.createAuthor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(authorService.deleteById(id));
    }
}
