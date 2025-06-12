package com.library.loanMicroservice.dto;

import com.library.loanMicroservice.model.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Representa um livro")
public class BookDTO {
    @Schema(hidden = true)
    private Long id;

    @Schema(description = "Título do livro", example = "Dom Casmurro")
    private String title;

    @Schema(description = "Ano de publicação do livro", example = "1899")
    private Integer year_publication;

    @Schema(description = "Nome do autor do livro", example = "Machado de Assis")
    private String authorName;

    @Schema(description = "Gênero do livro", example = "Romance")
    private Genre genre;

    @Schema(hidden = true)
    private Long authorId;

    @Schema(hidden = true)
    private Long genreId;
}
