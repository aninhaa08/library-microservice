package com.library.loanMicroservice.dto;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Representa um autor")
public class AuthorDto {

    @NotBlank
    @Schema(description = "Nome do autor", example = "Machado de Assis")
    private String name;
  
    @NotNull
    private LocalDate birthDate;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotNull LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@NotNull LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
