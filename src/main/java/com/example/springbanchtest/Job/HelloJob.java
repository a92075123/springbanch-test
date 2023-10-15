package com.example.springbanchtest.Job;

import com.example.springbanchtest.Job.processor.CustomizeItemProcessor;
import com.example.springbanchtest.bean.User;
import com.example.springbanchtest.tool.TwilioConfig;
import com.twilio.Twilio;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;

import javax.annotation.PostConstruct;

@Configuration
@EnableBatchProcessing
public class HelloJob {

    @Autowired
    private  TwilioConfig twilioConfig;

    @PostConstruct
    public void initTwilio(){

        Twilio.init(twilioConfig.getAccount_sid(),twilioConfig.getAuth_token());
    }



    @Bean
    public Job helloJob1(JobBuilderFactory jobBuilderFactory, Step myStep) {
        return jobBuilderFactory.get("helloJob4")
                .start(myStep)
                .incrementer(new RunIdIncrementer())
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        System.out.println("要準備執行作業");
//                        Message.creator(new PhoneNumber("+886"),//to 要寄給誰
//                                new PhoneNumber(twilioConfig.getTrial_number()),//twilio本身的電話
//                                "test").create();

                    }
                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        System.out.println("作業執行完");


                        return ExitStatus.COMPLETED;
                    }
                })
                .build();
    }

    @Bean
    public Step myStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("myStep")
                .<User,User>chunk(3)
                .reader(itemReader())
                .processor(customizeProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public FlatFileItemReader<User> itemReader(){
        return new FlatFileItemReaderBuilder<User>()
                .name("userItemReader")//對構造器取名子
                //設置文件
                .resource(new ClassPathResource("user.txt"))
                //解析數據--指定解析器使用#號分割--默認是,號
                .delimited().delimiter("#")
                //按照#號，數據怎麼命名
                .names("id","name","age")
                //封裝數據 將讀取的數據封裝到對象:User 對象
                .targetType(User.class)
                .build();
    }

    @Bean
    public CustomizeItemProcessor customizeProcessor(){
        return new CustomizeItemProcessor();
    }

    @Bean
    public FlatFileItemWriter<User> itemWriter(){
        return   new FlatFileItemWriterBuilder<User>()
                .name("userItemWriter")
                .resource(new PathResource("c:/works/outUser.txt"))//輸出的位置
                .formatted()//要進行格式輸出
                .format("id:%s,姓名:%s,年齡:%s")//輸出數據的格式
                .names("id","name","age")//根據itemReader的.names給於每個%相對應的值
                //.shouldDeleteIfEmpty(true) 如果數據為空，創建文件會直接被刪除
                //.shouldDeleteIfExists(true) 輸出文件如果存在，則刪除
                //.append(true)如果輸出文件存在，不刪除，直接加到現有文件中
                .build();
    }





}
