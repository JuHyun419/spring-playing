package io.spring.springbatch.scope;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JobScopeStepScopeConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job scopeJob() {
        return jobBuilderFactory.get("scopeJob")
                .start(scopeStep1(null))
                .next(scopeStep2())
                .listener(new JobListener())
                .build();
    }

    @Bean
    @JobScope // Step의 Proxy 객체 생성
    public Step scopeStep1(@Value("#{jobParameters['message']}") String message) {
        System.out.println("jobParameters['message'] : " + message);
        return stepBuilderFactory.get("step1")
                .tasklet(scopeTasklet(null))
                .build();
    }

    @Bean
    public Step scopeStep2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    @StepScope
    public Tasklet scopeTasklet(@Value("#{jobExecutionContext['name']}") String name) {
        return (stepContribution, chunkContext) -> {
            System.out.println("jobExecutionContext['name'] : " + name);
            return RepeatStatus.FINISHED;
        };
    }
}
