package com.jh.resilience4j.ratelimiter;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rate-limiter")
public class RateLimiterController {
    
    private final RateLimiterService service;

    public RateLimiterController(RateLimiterService service) {
        this.service = service;
    }

    @GetMapping
//    @RateLimiter(name = "rate-limiter", fallbackMethod = "rateLimiterFallback")
    @RateLimiter(name = "rate-limiter")
    public String testRateLimiter() {
        System.out.println("testRateLimiter called!");
        return service.test();
    }

//    public String rateLimiterFallback(Exception e) {
//        System.out.println("fallback called! message: " + e.getMessage());
//
//        return "fallback";
//    }
}
