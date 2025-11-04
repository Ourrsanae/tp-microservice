package com.example.productcompositeservice.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CompositeMetrics extends OncePerRequestFilter {

    private final Counter postPutCounter;
    private final Counter getCounter;

    public CompositeMetrics(MeterRegistry meterRegistry) {
        this.postPutCounter = Counter.builder("composite.requests.post_put.total")
                .description("Nombre total de requêtes POST et PUT reçues par le service composite")
                .register(meterRegistry);

        this.getCounter = Counter.builder("composite.requests.get.total")
                .description("Nombre total de requêtes GET reçues par le service composite")
                .register(meterRegistry);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String method = request.getMethod();

        if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method)) {
            postPutCounter.increment();
        } else if ("GET".equalsIgnoreCase(method)) {
            getCounter.increment();
        }

        filterChain.doFilter(request, response);
    }
}
