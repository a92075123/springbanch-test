package com.example.springbanchtest.Job.flow;

import com.example.springbanchtest.Job.reader.DbReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.test}")
    public String test;



    int a =10 ;

    @Bean
    public Flow flowS1A() throws Exception {
        return new FlowBuilder<Flow>("flowS1A")
                .start(step1())
                .next(step2()).on("*").to(step3())
                .next(step4())
                .build();
    }

    @Bean
    public Step step1(){

        return stepBuilderFactory.get("step1").tasklet(tasklet())
                .build();
    }


    public Step step2(){
        return stepBuilderFactory.get("step2").tasklet(tasklet2())
                .build();

    }

    public Step step3(){
        return stepBuilderFactory.get("step3").tasklet(tasklet3())
                .build();

    }

    public Step step4(){
        return stepBuilderFactory.get("step4").tasklet(tasklet4())
                .build();

    }



    @Bean
    public Tasklet tasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                System.out.println(test);
                System.out.println("---------1----------");


                return RepeatStatus.FINISHED;
            }
        };
    }

    @Bean
    public Tasklet tasklet2() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                System.out.println("====2=====");

                try {
                   int  b =  10/0 ;
                }catch (Exception e){
                    throw e;

                }
                return RepeatStatus.FINISHED;
            }
        };
    }

    @Bean
    public Tasklet tasklet3() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                System.out.println("=============3============");

                if(a == 10 ){
                    throw new RuntimeException("假裝失敗了");
                }

                return RepeatStatus.FINISHED;
            }
        };
    }
    @Bean
    public Tasklet tasklet4() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                System.out.println("========4==============");

                return RepeatStatus.FINISHED;
            }
        };
    }

}
