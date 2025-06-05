package com.library.loanMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.loanMicroservice.service.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController {
    
    @Autowired
    private BooksService booksService;

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(this.booksService.deleteById(id));
    }
}
