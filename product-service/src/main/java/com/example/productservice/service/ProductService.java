package com.example.productservice.service;

import com.example.productservice.dao.ProductRepository;
import com.example.productservice.domain.Product;
import com.example.productservice.dto.ProductDTO;
import com.example.productservice.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public ProductDTO addProduct(ProductDTO dto) {
        Product p = Product.builder()
                .productId(dto.getProductId())
                .name(dto.getName())
                .weight(dto.getWeight())
                .build();
        Product saved = repo.save(p);
        return new ProductDTO(saved.getProductId(), saved.getName(), saved.getWeight());
    }

    //  ICI : la méthode getProduct
    public ProductDTO getProduct(Long id) {
        Product p = repo.findByProductId(id)
                .orElseThrow(() -> new ProductNotFoundException("Produit introuvable : " + id));
        return new ProductDTO(p.getProductId(), p.getName(), p.getWeight());
    }

    public void deleteProduct(Long id) {
        repo.deleteByProductId(id);
    }
}
