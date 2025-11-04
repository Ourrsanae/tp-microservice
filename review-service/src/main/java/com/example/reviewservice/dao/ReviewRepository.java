package com.example.reviewservice.dao;

import com.example.reviewservice.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);
    void deleteByProductId(Long productId);
}
