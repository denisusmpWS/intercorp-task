package com.client.service.intercorp.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfig {

    int ringBufferSizeInClosedState = 45;
    int ringBufferSizeInHalfOpenState = 10;
    float failureRateThreshold = Float.parseFloat("50.0");
    int waitDurationInOpenState = 3000;

    @Bean(name = "intercorp.default")
    CircuitBreaker intercoprBreaker() {
        io.github.resilience4j.circuitbreaker.CircuitBreakerConfig config = io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.custom()
                .ringBufferSizeInClosedState(ringBufferSizeInClosedState)
                .ringBufferSizeInHalfOpenState(ringBufferSizeInHalfOpenState)
                .failureRateThreshold(failureRateThreshold)
                .waitDurationInOpenState(Duration.ofMillis(waitDurationInOpenState)).build();
        return CircuitBreaker.of("intercorp.default", config);
    }

}