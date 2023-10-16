package com.example.springbanchtest.Job;

import com.example.springbanchtest.Job.flow.FlowS1;
import com.example.springbanchtest.Job.flow.FlowS2;
import com.example.springbanchtest.Job.listener.JobStateListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class TestJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    //創造一個step對象執行的任務
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private FlowS1 flowS1;

    @Autowired
    private FlowS2 flowS2;

    @Bean
    public JobStateListener jobStateListener(){
        return new JobStateListener();
    }

    @Bean
    public Job testJob1() throws Exception {
        return jobBuilderFactory.get("testJob1")
                .start(flowS1.flowS1A())
                .end()
                .listener(jobStateListener())
                .build();
    }

}
