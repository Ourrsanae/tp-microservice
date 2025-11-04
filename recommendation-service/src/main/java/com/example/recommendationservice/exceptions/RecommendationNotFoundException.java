package com.example.recommendationservice.exceptions;

public class RecommendationNotFoundException extends RuntimeException {
    public RecommendationNotFoundException(String message) {
        super(message);
    }
}
