package io.spring.springbatch.chunk;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ChunkConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job chunkJob() {
        return jobBuilderFactory.get("chunkJob")
                .start(chunkStep())
                .next(taskletStep())
                .build();
    }

    @Bean
    public Step chunkStep() {
        return stepBuilderFactory.get("chunkStep1")
                .<String, String>chunk(10)
                .reader(new ListItemReader<>(List.of("item1", "item2", "item3", "item4", "item5")))
                .processor((ItemProcessor<String, String>) item -> {
                    Thread.sleep(300);
                    System.out.println("reader item: " + item);
                    return "my " + item;
                })
                .writer(items -> {
                    Thread.sleep(300);
                    System.out.println("write items: " + items);
                })
                .build();
    }

    @Bean
    public Step taskletStep() {
        return stepBuilderFactory.get("taskletStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("taskletStep has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
