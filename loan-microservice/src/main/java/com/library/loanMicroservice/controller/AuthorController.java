package com.library.loanMicroservice.controller;

import com.library.loanMicroservice.dto.AuthorDto;
import com.library.loanMicroservice.model.Author;
import com.library.loanMicroservice.repository.AuthorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.loanMicroservice.service.AuthorService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "Autores", description = "Operações relacionadas aos autores")
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Operation(summary = "Cria um novo autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autor criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o autor"),
            @ApiResponse(responseCode = "409", description = "Autor já existente"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
  
    @PostMapping("/postAuthor")
    public ResponseEntity<Author> createAuthor(@RequestBody @Valid AuthorDto dto){
        Author saved = this.authorService.createAuthor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Exibir todos os autores", description = "Exibe a lista de autores cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autores retornados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum autor encontrado")
    })

    @GetMapping("/getAuthors")
    public List<Author> getAllAuthors() {
        return authorService.getAuthor();
    }

    @Operation(summary = "Busca um autor existente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @GetMapping("/getAuthor/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }

    @Operation(summary = "Atualiza um autor existente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o autor"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @PutMapping("/putAuthor/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody @Valid AuthorDto dto){
        Author updated = this.authorService.updateAuthor(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Excluir autor por ID", description = "Exclui um autor existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido")
    })

    @DeleteMapping("/deleteAuthor/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(authorService.deleteById(id));
    }
}
