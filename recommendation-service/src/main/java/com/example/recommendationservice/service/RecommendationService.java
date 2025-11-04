package com.example.recommendationservice.service;

import com.example.recommendationservice.dao.RecommendationRepository;
import com.example.recommendationservice.domain.Recommendation;
import com.example.recommendationservice.dto.RecommendationDTO;
import com.example.recommendationservice.exceptions.RecommendationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository repository;

    public Recommendation addRecommendation(RecommendationDTO dto) {
        Recommendation rec = new Recommendation();
        rec.setProductId(dto.getProductId());
        rec.setRecommendationId(dto.getRecommendationId());
        rec.setAuthor(dto.getAuthor());
        rec.setRate(dto.getRate());
        rec.setContent(dto.getContent());
        return repository.save(rec);
    }

    public List<Recommendation> getRecommendationsByProduct(Long productId) {
        List<Recommendation> list = repository.findByProductId(productId);
        if (list.isEmpty()) {
            throw new RecommendationNotFoundException("Aucune recommandation trouvée pour le produit ID : " + productId);
        }
        return list;
    }

    public void deleteRecommendationsByProduct(Long productId) {
        List<Recommendation> list = repository.findByProductId(productId);
        if (list.isEmpty()) {
            throw new RecommendationNotFoundException("Aucune recommandation à supprimer pour le produit ID : " + productId);
        }
        repository.deleteAll(list);
    }
}
