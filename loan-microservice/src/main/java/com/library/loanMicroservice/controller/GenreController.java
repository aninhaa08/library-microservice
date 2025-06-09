package com.library.loanMicroservice.controller;

import com.library.loanMicroservice.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Gêneros", description = "Operações relacionadas aos gêneros")
@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Operation(summary = "Excluir gênero por ID", description = "Exclui um gênero existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gênero deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Gênero não encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido")
    })
    @DeleteMapping("/deleteGenre/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        genreService.deleteById(id);
        return ResponseEntity.ok("Gênero deletado com sucesso.");
    }
}
