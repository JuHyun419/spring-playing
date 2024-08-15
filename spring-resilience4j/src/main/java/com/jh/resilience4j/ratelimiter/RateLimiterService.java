package com.jh.resilience4j.ratelimiter;

import org.springframework.stereotype.Service;

@Service
public class RateLimiterService {

    public String test() {
        return "success";
    }
}
