package io.spring.springbatch.retry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.List;

@Configuration
public class RetryConfig {

    private static final int RETRY_COUNT = 3;
    private static final long BACK_OFF_DELAY = 1000L;

    @Bean
    public RetryTemplate retryTemplate() {
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(RETRY_COUNT);

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(BACK_OFF_DELAY);

        List<Class<? extends Throwable>> retryOn = List.of(RuntimeException.class);
        List<Class<? extends Throwable>> notRetryOn = List.of(NullPointerException.class);

        return RetryTemplate.builder()
                .customPolicy(retryPolicy)    // 재시도 횟수 설정
                .customBackoff(backOffPolicy) // 재시도 간격 설정
                .retryOn(retryOn)       // 재시도할 예외 설정
//                .notRetryOn(notRetryOn) // 재시도를 제외할 예외 설정
//                .maxAttempts(RETRY_COUNT)
//                .fixedBackoff(BACK_OFF_DELAY)
                .build();
    }

}
