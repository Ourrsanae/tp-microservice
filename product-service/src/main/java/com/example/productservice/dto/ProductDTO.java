package com.example.productservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductDTO {

    @NotNull(message = "L'identifiant du produit ne peut pas être nul")
    @Positive(message = "L'identifiant du produit doit être positif")
    private Long productId;

    @NotBlank(message = "Le nom du produit est obligatoire")
    private String name;

    @NotNull(message = "Le poids ne peut pas être nul")
    @DecimalMin(value = "0.0", inclusive = true, message = "Le poids doit être supérieur ou égal à 0.0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Le poids doit être inférieur ou égal à 100.0")
    private Double weight;
}
