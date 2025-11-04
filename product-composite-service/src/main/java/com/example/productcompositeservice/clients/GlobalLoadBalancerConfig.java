package com.example.productcompositeservice.clients;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@LoadBalancerClients({
        @LoadBalancerClient(name = "product-service"),
        @LoadBalancerClient(name = "review-service"),
        @LoadBalancerClient(name = "recommendation-service")
})
public class GlobalLoadBalancerConfig {

    @Bean
    @LoadBalanced
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}
