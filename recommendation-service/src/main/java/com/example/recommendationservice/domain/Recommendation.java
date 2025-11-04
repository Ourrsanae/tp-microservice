package com.example.recommendationservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long productId;

    @NotNull
    private Long recommendationId;

    @NotBlank
    private String author;

    @NotBlank
    private String rate;

    @NotBlank
    private String content;
}
