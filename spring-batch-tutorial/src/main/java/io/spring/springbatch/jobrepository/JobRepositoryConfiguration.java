package io.spring.springbatch.jobrepository;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@RequiredArgsConstructor
@Configuration
public class JobRepositoryConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobRepositoryListener jobRepositoryListener;

    @Primary
    @Bean
    public Job jobRepositoryJob() {
        return this.jobBuilderFactory.get("jobRepositoryJob")
                .start(jobRepositoryStep1())
                .next(jobRepositoryStep2())
                .listener(jobRepositoryListener) // JobExecutionListener 적용
                .build();
    }

    @Bean
    public Step jobRepositoryStep1() {
        return stepBuilderFactory.get("jobRepositoryStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
    @Bean
    public Step jobRepositoryStep2() {
        return stepBuilderFactory.get("jobRepositoryStep2")
                .tasklet((contribution, chunkContext) -> null)
                .build();
    }
}
