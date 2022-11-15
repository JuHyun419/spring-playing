package io.spring.springbatch.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ValidatorConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job parametersValidatorJob() {
        String[] requiredKeys = {"name", "date"};
        String[] optionalKeys = {"year"};

        return this.jobBuilderFactory.get("parametersValidatorJob")
//                .validator(new CustomJobParametersValidator())
                .validator(new DefaultJobParametersValidator(requiredKeys, optionalKeys))
                .start(parametersValidatorStep1())
                .next(parametersValidatorStep2())
                .next(parametersValidatorStep3())
                .build();
    }

    @Bean
    public Step parametersValidatorStep1() {
        return stepBuilderFactory.get("parametersValidatorStep1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("parametersValidatorStep1 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step parametersValidatorStep2() {
        return stepBuilderFactory.get("parametersValidatorStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("parametersValidatorStep2 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step parametersValidatorStep3() {
        return stepBuilderFactory.get("parametersValidatorStep3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("parametersValidatorStep3 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
