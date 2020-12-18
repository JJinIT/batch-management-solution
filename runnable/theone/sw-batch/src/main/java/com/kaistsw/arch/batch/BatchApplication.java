package com.kaistsw.arch.batch;

import com.kaistsw.arch.batch.job.sample.SampleJobTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class BatchApplication {
	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Bean
	public Job job() throws Exception {
		System.out.println("job!!!");
		return this.jobs.get("job").start(step1()).build();
	}

	@Bean
	protected Step step1() throws Exception {
		System.out.println("step1!!!");
		return this.steps.get("step1").tasklet(new SampleJobTasklet()).build();
	}

	public static void main(String[] args) {
		System.out.println("hello?");
		SpringApplication.run(BatchApplication.class, args);
	}

}

