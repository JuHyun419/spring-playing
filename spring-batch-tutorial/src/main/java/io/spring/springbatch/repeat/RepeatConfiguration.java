package io.spring.springbatch.repeat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.repeat.exception.SimpleLimitExceptionHandler;
import org.springframework.batch.repeat.support.RepeatTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class RepeatConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job repeatJob() throws Exception {
        return jobBuilderFactory.get("repeatJob")
                .incrementer(new RunIdIncrementer())
                .start(repeatStep())
                .build();
    }

        @Bean
        public Step repeatStep () {

            return stepBuilderFactory.get("repeatStep1")
                    .<String, String>chunk(5)
                    .reader(new ItemReader<>() {
                        int i = 0;

                        @Override
                        public String read() {
                            i++;
                            return i > 3 ? null : "item " + i;
                        }
                    })
                    .processor(new ItemProcessor<>() {
                        // RepeatStatus, CompletionPolicy, ExceptionHandler
                        final RepeatTemplate repeatTemplate = new RepeatTemplate();

                        @Override
                        public String process(String item) throws Exception {
                            // 반복할 때마다 count 변수의 값을 1씩 증가 시키고, count 값이 chunkSize 보다 크거나 같으면 반복 종료
                            //repeatTemplate.setCompletionPolicy(new SimpleCompletionPolicy(3));

                        /*
                        // 소요된 시간이 설정된 시간보다 클 경우 반복문 종료 (3초동안 반복 후 작업 종료)
//                        template.setCompletionPolicy(new TimeoutTerminationPolicy(3000));

                        // 여러 유형의 CompletionPolicy 를 복합적으로 처리함
                        // 여러 개 중에 먼저 조건에 부합하는(OR 조건) CompletionPolicy 에 따라 반복문이 종료됨
                        CompositeCompletionPolicy completionPolicy = new CompositeCompletionPolicy();
                        CompletionPolicy[] completionPolicies = new CompletionPolicy[]{
                                new TimeoutTerminationPolicy(3000),
                                new SimpleCompletionPolicy(2)
                        };
                        completionPolicy.setPolicies(completionPolicies);
                        template.setCompletionPolicy(completionPolicy);
                        */

                            // 예외 제한 횟수만큼 반복문 실행
                            repeatTemplate.setExceptionHandler(simpleLimitExceptionHandler());

                            repeatTemplate.iterate(context -> {
                                // 비즈니스 로직 구현
                                log.info("RepeatTemplate test");

                                throw new RuntimeException("Exception is occurred");
                            });

                            return item;
                        }
                    })
                    .writer(System.out::println)
                    .build();
        }


        @Bean
        public SimpleLimitExceptionHandler simpleLimitExceptionHandler () {
            return new SimpleLimitExceptionHandler(5);
        }
    }

