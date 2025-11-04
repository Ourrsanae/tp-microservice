package com.example.recommendationservice.controller;

import com.example.recommendationservice.domain.Recommendation;
import com.example.recommendationservice.dto.RecommendationDTO;
import com.example.recommendationservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService service;

    @PostMapping
    public Recommendation create(@RequestBody RecommendationDTO dto) {
        return service.addRecommendation(dto);
    }

    @GetMapping("/product/{productId}")
    public List<Recommendation> getByProduct(@PathVariable Long productId) {
        return service.getRecommendationsByProduct(productId);
    }

    @DeleteMapping("/product/{productId}")
    public String deleteByProduct(@PathVariable Long productId) {
        service.deleteRecommendationsByProduct(productId);
        return "Les recommandations du produit " + productId + " ont été supprimées avec succès.";
    }
}
