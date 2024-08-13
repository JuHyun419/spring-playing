package com.jh.resilience4j.circuitbreaker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CircuitBreakerController {

    private final CircuitBreakerService service;

    public CircuitBreakerController(CircuitBreakerService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public String testCircuitBreaker(@PathVariable String id) {
        service.test(id);

        return "success";
    }

}
