package com.library.loanMicroservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para criação/atualização de gênero")
public class GenreDto {

    @Schema(description = "Nome do gênero", example = "Ficção Científica", required = true)
    @NotBlank(message = "O nome do gênero é obrigatório")
    private String name;

    public GenreDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

