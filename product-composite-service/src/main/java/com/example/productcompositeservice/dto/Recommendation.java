package com.example.productcompositeservice.dto;

import lombok.Data;

@Data
public class Recommendation {
    private Long productId;
    private Long recommendationId;
    private String author;
    private String rate;
    private String content;
}

