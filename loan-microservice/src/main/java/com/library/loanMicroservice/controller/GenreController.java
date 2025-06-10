package com.library.loanMicroservice.controller;

import com.library.loanMicroservice.model.Genre;
import com.library.loanMicroservice.repository.GenreRepository;
import com.library.loanMicroservice.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "Gêneros", description = "Operações relacionadas aos gêneros")
@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreRepository genreRepository;

    @Operation(summary = "Excluir gênero por ID", description = "Exclui um gênero existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gênero deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Gênero não encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido")
    })

    @DeleteMapping("/deleteGenre/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(genreService.deleteById(id));
    }

    
    @GetMapping
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @GetMapping("/{id}")
    public Genre getById(@PathVariable Long id) {
        return genreRepository.findById(id)
             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gênero não encontrado."));
    }

    @PostMapping
    public ResponseEntity<Genre> create(@RequestBody Genre genre) {
        System.out.println(genre);
        return new ResponseEntity<>(this.genreRepository.save(genre), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> update(@PathVariable Long id, @RequestBody Genre generoAtualizado) {
        Optional<Genre> optionalGenero = genreRepository.findById(id);

        if (!optionalGenero.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gênero não encontrado.");
        }

        Genre genero = optionalGenero.get();
        genero.setName(generoAtualizado.getName());

        genreRepository.save(genero);

        return ResponseEntity.ok(genero);
    }
}
