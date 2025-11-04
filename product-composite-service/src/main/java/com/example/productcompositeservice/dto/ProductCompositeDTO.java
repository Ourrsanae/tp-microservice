package com.example.productcompositeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCompositeDTO {
    private Product product;
    private List<Review> reviews;
    private List<Recommendation> recommendations;
}
