package com.example.springbanchtest.Job.reader;


import com.example.springbanchtest.Job.rowmapper.EmpRowMapper;
import com.example.springbanchtest.bean.Emp;

import com.example.springbanchtest.tool.Mybatis;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.sql.DataSource;
import java.io.IOException;



@Configuration
@EnableBatchProcessing
public class DbReader {


    @Autowired
    private DataSource dataSource;
    @Autowired
    private Mybatis mybatisDB;



    @Bean
    public EmpRowMapper empRowMapper (){
        return new EmpRowMapper();
    }


    @Bean
    public MyBatisCursorItemReader<Emp> myItemReader() throws Exception {
        return new MyBatisCursorItemReaderBuilder<Emp>()
                .sqlSessionFactory(mybatisDB.sqlSessionFactory(dataSource))
                .queryId("EmpMapper.findAll")
                .build();

    }

}
