package io.spring.springbatch.jobinstance;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JobInstanceConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

//    @Primary
    @Bean
    public Job instanceBatchJob() {
        return this.jobBuilderFactory.get("Job")
                .start(instanceStep1())
                .next(instanceStep2())
                .build();
    }

    @Bean
    public Step instanceStep1() {
        return stepBuilderFactory.get("instanceStep1")
                .tasklet((contribution, chunkContext) -> {
                    JobInstance jobInstance = contribution.getStepExecution().getJobExecution().getJobInstance();
                    System.out.println("jobInstance.getId() : " + jobInstance.getId());
                    System.out.println("jobInstance.getInstanceId() : " + jobInstance.getInstanceId());
                    System.out.println("jobInstance.getJobName() : " + jobInstance.getJobName());
                    System.out.println("jobInstance.getJobVersion : " + jobInstance.getVersion());
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step instanceStep2() {
        return stepBuilderFactory.get("instanceStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
