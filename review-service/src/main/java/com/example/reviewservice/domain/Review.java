package com.example.reviewservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(indexes = @Index(name = "idx_review_product", columnList = "productId"))
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Positive
    private Long productId;

    @NotNull @Positive
    private Long reviewId;

    @NotBlank
    private String author;

    @NotBlank
    private String subject;

    @NotBlank
    private String content;
}
