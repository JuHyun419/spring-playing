package io.spring.springbatch.joblauncher;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JobLauncherConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobLauncherJob() {
        return this.jobBuilderFactory.get("jobLauncherJob")
                .start(jobLauncherStep1())
                .next(jobLauncherStep2())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step jobLauncherStep1() {
        return stepBuilderFactory.get("jobLauncherStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        Thread.sleep(3000);
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step jobLauncherStep2() {
        return stepBuilderFactory.get("jobLauncherStep2")
                .tasklet((contribution, chunkContext) -> null)
                .build();
    }
}
