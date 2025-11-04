package com.example.productcompositeservice.clients;

import com.example.productcompositeservice.dto.Recommendation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "recommendation-service")
public interface RecommendationClient {

    @GetMapping("/recommendations/product/{productId}")
    List<Recommendation> getRecommendations(@PathVariable("productId") Long productId);
}
