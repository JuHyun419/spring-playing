//package com.jh.resilience4j.circuitbreaker;
//
//import io.github.resilience4j.circuitbreaker.CircuitBreaker;
//import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
//import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.Duration;
//
//@Configuration
//public class CircuitBreakerConfiguration {
//
//    private final CircuitBreakerRegistry circuitBreakerRegistry;
//
//    public CircuitBreakerConfiguration(CircuitBreakerRegistry circuitBreakerRegistry) {
//        this.circuitBreakerRegistry = circuitBreakerRegistry;
//    }
//
//    /**
//     * 3회 이상 호출 후 최근 5개의 결과가 실패율이 50% 이상이라면, Circuit 이 오픈되어 요청을 차단한다.
//     * @return
//     */
//    @Bean
//    public CircuitBreaker circuitBreaker() {
//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//                .failureRateThreshold(50) // 실패율 임계값 %
//                .minimumNumberOfCalls(3) // 서킷브레이커가 값을 집계하기 전, 최소 호출해야 하는 횟수
//                .slowCallDurationThreshold(Duration.ofMillis(3000)) // 호출시간 임계값
//                .waitDurationInOpenState(Duration.ofMillis(5000)) // OPEN -> HALF_OPEN 변경시키기 전까지 대기하는 시간
//                .slidingWindowSize(5) // size
//                .build();
//
//        return circuitBreakerRegistry.circuitBreaker("jh", circuitBreakerConfig);
//    }
//
//}
