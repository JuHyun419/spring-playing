package io.spring.springbatch.jobparameters;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JobParameterConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job BatchJob() {
        return this.jobBuilderFactory.get("JobParameters")
                .start(parametersStep1(null))
                .next(parametersStep2())
                .build();
    }

    @Bean
    @JobScope
    public Step parametersStep1(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return stepBuilderFactory.get("JobParametersStep1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("=======================");
                    System.out.println("requestDate: " + requestDate);
                    System.out.println("=======================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step parametersStep2() {
        return stepBuilderFactory.get("JobParametersStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step1 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
