package com.example.reviewservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewDTO {
    private Long productId;
    private Long reviewId;
    private String author;
    private String subject;
    private String content;
}
