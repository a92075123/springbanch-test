package com.example.springbanchtest.Job.flow;

import com.example.springbanchtest.Job.reader.DbReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class FlowS1 {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DbReader dbReader;

//    @Bean
//    public Flow flowA(){
//        return new FlowBuilder<Flow>("flowA")
//                .start(stepA())
//                .build();
//    }



//    @Bean
//    public Step stepA(){
//        return stepBuilderFactory.get("StepA")
//                .chunk(3)
//                .reader(dbReader.itemReader())
//                .processor()
//                .writer()
//                .build();
//    }
}
