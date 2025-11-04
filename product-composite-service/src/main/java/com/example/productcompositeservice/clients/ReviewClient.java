package com.example.productcompositeservice.clients;

import com.example.productcompositeservice.dto.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "review-service")
public interface ReviewClient {

    @GetMapping("/reviews/product/{productId}")
    List<Review> getReviews(@PathVariable("productId") Long productId);
}
