package com.example.productcompositeservice.dto;

import lombok.Data;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {
    private Long productId;
    private Long reviewId;
    private String author;
    private String subject;
    private String content;
}
