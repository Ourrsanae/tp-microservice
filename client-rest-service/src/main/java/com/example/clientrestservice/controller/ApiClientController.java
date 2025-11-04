package com.example.clientrestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ApiClientController {

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8086/product-composite";

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getProductComposite(@PathVariable Long id) {
        String url = BASE_URL + "/" + id;
        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProductComposite() {
        String url = BASE_URL;

        Map<String, Object> body = new HashMap<>();
        body.put("productId", 10);
        body.put("name", "Produit Client REST");
        body.put("weight", 1.2);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProductComposite(@PathVariable Long id) {
        String url = BASE_URL + "/" + id;

        Map<String, Object> body = new HashMap<>();
        body.put("name", "Produit mis à jour");
        body.put("weight", 2.5);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        restTemplate.put(url, request);
        return ResponseEntity.ok("Produit mis à jour avec succès");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProductComposite(@PathVariable Long id) {
        String url = BASE_URL + "/" + id;
        restTemplate.delete(url);
        return ResponseEntity.ok("Produit supprimé avec succès");
    }
}
