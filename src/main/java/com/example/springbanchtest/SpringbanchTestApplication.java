package com.example.springbanchtest;

import com.example.springbanchtest.tool.TwilioConfig;
import com.twilio.Twilio;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringbanchTestApplication {







	public static void main(String[] args) {




		ApplicationContext context = SpringApplication.run(SpringbanchTestApplication.class, args);

		JobLauncher jobLauncher = context.getBean(JobLauncher.class);
		Job job = context.getBean("testJob1", Job.class);


		JobParameters params = new JobParametersBuilder()
				.addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();

		try {
			JobExecution jobExecution = jobLauncher.run(job,params);
			System.out.println("Job Status : " + jobExecution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Job failed");
		}

	}

}
