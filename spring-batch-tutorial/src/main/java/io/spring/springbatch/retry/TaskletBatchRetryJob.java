package io.spring.springbatch.retry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.retry.support.RetryTemplate;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TaskletBatchRetryJob {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final RetryTemplate retryTemplate;

    @Primary
    @Bean
    public Job retryJob() {
        return jobBuilderFactory.get("retryJob")
                .start(retryStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step retryStep() {
        return stepBuilderFactory.get("retryStep")
                .tasklet(retryTasklet())
                .build();
    }

    @Bean
    public Tasklet retryTasklet() {
        return ((contribution, chunkContext) -> {
            retryTemplate.execute(
                    context -> { // RetryCallback
                        log.info("TaskletBatchRetryJob run ...");
                        throw new RuntimeException();
                    },
                    context -> { // RecoveryCallback
                        log.info("Failed retry 3 count ...");
                        return null;
                    });
            return RepeatStatus.FINISHED;
        });
    }
}
