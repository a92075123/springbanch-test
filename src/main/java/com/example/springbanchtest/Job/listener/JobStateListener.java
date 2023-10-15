package com.example.springbanchtest.Job.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;


public class JobStateListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("執行前");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("執行後");

    }
}
