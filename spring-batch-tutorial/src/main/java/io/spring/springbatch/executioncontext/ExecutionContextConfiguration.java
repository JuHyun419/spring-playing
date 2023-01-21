package io.spring.springbatch.executioncontext;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ExecutionContextConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final ExecutionContextTasklet1 executionContextTasklet1;
    private final ExecutionContextTasklet2 executionContextTasklet2;
    private final ExecutionContextTasklet3 executionContextTasklet3;
    private final ExecutionContextTasklet4 executionContextTasklet4;

//    @Primary
    @Bean
    public Job executionContextJob() {
        return this.jobBuilderFactory.get("executionContextJob")
                .start(executionContextStep1())
                .next(executionContextStep2())
                .next(executionContextStep3())
                .next(executionContextStep4())
                .build();
    }

    @Bean
    public Step executionContextStep1() {
        return stepBuilderFactory.get("executionContextStep1")
                .tasklet(executionContextTasklet1)
                .build();
    }

    @Bean
    public Step executionContextStep2() {
        return stepBuilderFactory.get("executionContextStep2")
                .tasklet(executionContextTasklet2)
                .build();
    }

    @Bean
    public Step executionContextStep3() {
        return stepBuilderFactory.get("executionContextStep3")
                .tasklet(executionContextTasklet3)
                .build();
    }

    @Bean
    public Step executionContextStep4() {
        return stepBuilderFactory.get("executionContextStep4")
                .tasklet(executionContextTasklet4)
                .build();
    }
}
