package com.example.recommendationservice.dao;

import com.example.recommendationservice.domain.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByProductId(Long productId);
}
