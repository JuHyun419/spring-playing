package io.spring.springbatch.jobexecution;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JobExecutionConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

//    @Primary
    @Bean
    public Job executionJob() {
        return this.jobBuilderFactory.get("JobExecution")
                .start(executionStep1())
                .next(executionStep2())
                .build();
    }

    @Bean
    public Step executionStep1() {
        return stepBuilderFactory.get("JobExecutionStep1")
                .tasklet((contribution, chunkContext) -> {
                    JobExecution jobExecution = contribution.getStepExecution().getJobExecution();
                    System.out.println("jobExecution = " + jobExecution);

                    System.out.println("step1 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step executionStep2() {
        return stepBuilderFactory.get("JobExecutionStep2")
                .tasklet((contribution, chunkContext) -> {
//                    throw new RuntimeException("JobExecution has failed");
                    System.out.println("step2 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
