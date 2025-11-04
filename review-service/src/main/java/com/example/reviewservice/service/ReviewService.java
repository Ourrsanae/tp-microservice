package com.example.reviewservice.service;

import com.example.reviewservice.dao.ReviewRepository;
import com.example.reviewservice.domain.Review;
import com.example.reviewservice.dto.ReviewDTO;
import com.example.reviewservice.exceptions.ReviewNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository repo;

    // ✅ Injection via constructeur
    public ReviewService(ReviewRepository repo) {
        this.repo = repo;
    }

    public List<ReviewDTO> getReviewsByProduct(Long productId) {
        List<Review> reviews = repo.findByProductId(productId);
        if (reviews.isEmpty())
            throw new ReviewNotFoundException("Aucun avis trouvé pour le produit " + productId);

        return reviews.stream()
                .map(r -> new ReviewDTO(r.getProductId(), r.getReviewId(), r.getAuthor(), r.getSubject(), r.getContent()))
                .collect(Collectors.toList());
    }

    public ReviewDTO addReview(ReviewDTO dto) {
        Review review = Review.builder()
                .productId(dto.getProductId())
                .reviewId(dto.getReviewId())
                .author(dto.getAuthor())
                .subject(dto.getSubject())
                .content(dto.getContent())
                .build();
        Review saved = repo.save(review);
        return new ReviewDTO(saved.getProductId(), saved.getReviewId(), saved.getAuthor(), saved.getSubject(), saved.getContent());
    }

    public void deleteReviewsByProduct(Long productId) {
        repo.deleteByProductId(productId);
    }
}
