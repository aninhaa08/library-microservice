package com.library.loanMicroservice.dto;

import com.library.loanMicroservice.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private Integer year_publication;
    private String authorName;
    private Genre genre;
}
