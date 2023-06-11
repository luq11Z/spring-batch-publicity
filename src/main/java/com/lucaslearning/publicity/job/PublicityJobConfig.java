package com.lucaslearning.publicity.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublicityJobConfig {

	@Bean
	public Job publicityJob(JobRepository jobRepository, Step publicityStep) {
		return new JobBuilder("publicityJob", jobRepository)
				.start(publicityStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
}
