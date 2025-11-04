package com.example.productcompositeservice.service;

import com.example.productcompositeservice.clients.ProductClient;
import com.example.productcompositeservice.clients.ReviewClient;
import com.example.productcompositeservice.clients.RecommendationClient;
import com.example.productcompositeservice.dto.Product;
import com.example.productcompositeservice.dto.Review;
import com.example.productcompositeservice.dto.Recommendation;
import com.example.productcompositeservice.dto.ProductCompositeDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCompositeService {

    private final ProductClient productClient;
    private final ReviewClient reviewClient;
    private final RecommendationClient recommendationClient;

    // ✅ Circuit breaker appliqué au service global
    @CircuitBreaker(name = "productCompositeService", fallbackMethod = "getDefaultComposite")
    @Retry(name = "productCompositeService")
    public ProductCompositeDTO getProductById(Long productId) {
        log.info("🔍 Fetching composite product data for productId: {}", productId);

        Product product = productClient.getProduct(productId);
        List<Review> reviews = reviewClient.getReviews(productId);
        List<Recommendation> recommendations = recommendationClient.getRecommendations(productId);

        log.info("✅ Data retrieved -> Product: {}, Reviews: {}, Recommendations: {}",
                product.getName(), reviews.size(), recommendations.size());

        return new ProductCompositeDTO(product, reviews, recommendations);
    }

    // ✅ Signature correcte : même paramètres + Throwable
    public ProductCompositeDTO getDefaultComposite(Long productId, Throwable t) {
        log.warn("⚠ Fallback triggered for composite service: {}", t.getMessage());

        Product fallbackProduct = new Product(productId, "Produit indisponible", 0.0);
        return new ProductCompositeDTO(fallbackProduct, List.of(), List.of());
    }
}
