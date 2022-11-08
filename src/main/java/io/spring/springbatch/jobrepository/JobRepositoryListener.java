package io.spring.springbatch.jobrepository;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Component;

@Component
public class JobRepositoryListener implements JobExecutionListener {

    private final JobRepository jobRepository;

    public JobRepositoryListener(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        final String jobName = jobExecution.getJobInstance().getJobName();
        final JobParameters jobParameters = new JobParametersBuilder()
                        .addString("requestDate", "20210102")
                        .toJobParameters();

        final JobExecution lastExecution = jobRepository.getLastJobExecution(jobName, jobParameters);

        if (lastExecution != null) {
            for (StepExecution execution : lastExecution.getStepExecutions()) {
                BatchStatus status = execution.getStatus();
                System.out.println("BatchStatus = " + status.isRunning());
                System.out.println("BatchStatus = " + status.name());
            }
        }
    }
}
