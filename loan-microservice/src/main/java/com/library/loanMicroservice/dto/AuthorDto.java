package com.library.loanMicroservice.dto;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotBlank;


public class AuthorDto {

    @NotBlank
    private String name;

    private OffsetDateTime birthDate;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public OffsetDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(OffsetDateTime birthDate) {
        this.birthDate = birthDate;
    }
}