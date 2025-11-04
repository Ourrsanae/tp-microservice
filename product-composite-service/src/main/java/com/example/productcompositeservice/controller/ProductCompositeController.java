package com.example.productcompositeservice.controller;

import com.example.productcompositeservice.dto.ProductCompositeDTO;
import com.example.productcompositeservice.service.ProductCompositeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-composite")
@RequiredArgsConstructor
public class ProductCompositeController {

    private final ProductCompositeService compositeService;

    @GetMapping("/{productId}")
    public ProductCompositeDTO getProductById(@PathVariable Long productId) {
        return compositeService.getProductById(productId);
    }
}
