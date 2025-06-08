package com.library.loanMicroservice.controller;

import com.library.loanMicroservice.dto.AuthorDto;
import com.library.loanMicroservice.model.Author;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.loanMicroservice.service.AuthorService;
import org.springframework.web.server.ResponseStatusException;

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

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody @Valid AuthorDto dto){
        Author updated = this.authorService.updateAuthor(id, dto);
        return ResponseEntity.ok(updated);

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(authorService.deleteById(id));
    }
}
