package com.library.loanMicroservice.controller;

import com.library.loanMicroservice.dto.AuthorDto;
import com.library.loanMicroservice.dto.GenreDto;
import com.library.loanMicroservice.model.Author;
import com.library.loanMicroservice.model.Genre;
import com.library.loanMicroservice.repository.GenreRepository;
import com.library.loanMicroservice.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
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

    @Operation(summary = "Cria um novo gênero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Gênero criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o gênero"),
            @ApiResponse(responseCode = "409", description = "Gênero já existente"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @PostMapping
    public ResponseEntity<Genre> create(@RequestBody @Valid GenreDto dto){
        Genre saved = this.genreService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Exibir todos os gêneros", description = "Exibe a lista de gêneros cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gêneros retornados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum gênero encontrado")
    })

    @GetMapping
    public ResponseEntity<List<Genre>> getAll() {
        return ResponseEntity.ok(genreService.getAll());
    }

    @Operation(summary = "Busca um gênero existente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gênero encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Gênero não encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getById(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.getById(id));
    }


    @Operation(summary = "Atualiza um gênero existente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gênero atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o gênero"),
            @ApiResponse(responseCode = "404", description = "Gênero não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @PutMapping("/{id}")
    public ResponseEntity<Genre> update(@PathVariable Long id, @RequestBody @Valid GenreDto dto) {
        Optional<Genre> optionalGenero = genreRepository.findById(id);

        if (!optionalGenero.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gênero não encontrado.");
        }

        Genre genero = optionalGenero.get();
        genero.setName(dto.getName());

        genreRepository.save(genero);

        return ResponseEntity.ok(genero);
    }

    @Operation(summary = "Excluir gênero por ID", description = "Exclui um gênero existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gênero deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Gênero não encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(genreService.deleteById(id));
    }
}
