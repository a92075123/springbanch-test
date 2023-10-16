package com.example.springbanchtest;

import com.example.springbanchtest.Job.reader.DbReader;
import com.example.springbanchtest.bean.Emp;
import com.example.springbanchtest.mybatis.TestMyBatis01;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Configuration

class SpringbanchTestApplicationTests {

	@Value("${spring.test}")
	public String test ;
//	@Autowired
//	private DbReader dbReader;

	@Test
	void DbReader() throws Exception {

		System.out.println(test);



//		TestMyBatis01 testMyBatis01 = new TestMyBatis01();
//		testMyBatis01.findAll();
//
//		MyBatisCursorItemReader<Emp> reader = dbReader.myItemReader();
//
//		Emp item;
//
//		while ((item = reader.read()) != null) {
//
//			System.out.println(item.getId());
//		}
//
	}

}
