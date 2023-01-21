package io.spring.springbatch.scope;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        jobExecution.getExecutionContext().putString("name", "Lee");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Job Success!");
    }
}
