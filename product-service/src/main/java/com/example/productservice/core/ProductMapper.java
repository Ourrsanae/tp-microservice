package com.example.productservice.core;

import com.example.productservice.domain.Product;
import com.example.productservice.dto.ProductDTO;

public class ProductMapper {

    public static Product toEntity(ProductDTO dto) {
        return Product.builder()
                .productId(dto.getProductId())
                .name(dto.getName())
                .weight(dto.getWeight())
                .build();
    }

    public static ProductDTO toDTO(Product entity) {
        return ProductDTO.builder()
                .productId(entity.getProductId())
                .name(entity.getName())
                .weight(entity.getWeight())
                .build();
    }
}
