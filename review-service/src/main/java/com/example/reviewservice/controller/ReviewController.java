package com.example.reviewservice.controller;

import com.example.reviewservice.dto.ReviewDTO;
import com.example.reviewservice.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> create(@Valid @RequestBody ReviewDTO dto) {
        return ResponseEntity.ok(service.addReview(dto));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getReviewsByProduct(productId));
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Long productId) {
        service.deleteReviewsByProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
