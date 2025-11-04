package com.example.recommendationservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationDTO {
    private Long productId;
    private Long recommendationId;
    private String author;
    private String rate;
    private String content;
}
