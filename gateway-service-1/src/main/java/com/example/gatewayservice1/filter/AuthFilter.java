package com.example.gatewayservice1.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private final WebClient.Builder webClientBuilder;

    @Value("${authorization.service.url:http://localhost:8087/auth/verify}")
    private String authServiceUrl;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        var request = exchange.getRequest();
        var headers = request.getHeaders();

        String username = headers.getFirst("username");
        String password = headers.getFirst("password");

        if (username == null || password == null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return webClientBuilder.build()
                .get()
                .uri(authServiceUrl)
                .header("username", username)
                .header("password", password)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(role -> {
                    if ("UNAUTHORIZED".equals(role)) {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }
                    return chain.filter(exchange);
                });
    }

    @Override
    public int getOrder() {
        return -1; // s'exécute avant les autres filtres
    }
}
