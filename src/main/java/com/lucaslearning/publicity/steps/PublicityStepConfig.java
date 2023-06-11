package com.lucaslearning.publicity.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.PlatformTransactionManager;

import com.lucaslearning.publicity.domain.ClientProductInterest;

@Configuration
public class PublicityStepConfig {

	@Bean
	public Step publicityStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
			ItemReader<ClientProductInterest> publicityReader,
			ItemProcessor<ClientProductInterest, SimpleMailMessage> publicityProcessor,
			ItemWriter<SimpleMailMessage> publicityWriter) {
		return new StepBuilder("publicityStep", jobRepository)
				.<ClientProductInterest, SimpleMailMessage>chunk(1, transactionManager)
				.reader(publicityReader)
				.processor(publicityProcessor)
				.writer(publicityWriter)
				.build();
	}

}
