package com.jh.resilience4j.circuitbreaker;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class CircuitBreakerService {

    private static final String CIRCUIT_BREAKER_CONFIG_NAME = "jh";

    @CircuitBreaker(name = CIRCUIT_BREAKER_CONFIG_NAME, fallbackMethod = "fallback")
    public void test(String id) {
        if (id.equals("1")) {
            throw new RuntimeException();
        }

        if (id.equals("2")) {
            try {
                System.out.println("slow call ....");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void fallback(String id, CallNotPermittedException ex) {
        System.out.println("fallbackMethod CallNotPermittedException running! " + ex.getMessage());
    }
}
