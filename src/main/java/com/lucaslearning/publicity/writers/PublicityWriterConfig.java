package com.lucaslearning.publicity.writers;

import org.springframework.batch.item.mail.SimpleMailMessageItemWriter;
import org.springframework.batch.item.mail.builder.SimpleMailMessageItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

@Configuration
public class PublicityWriterConfig {

	@Bean
	public SimpleMailMessageItemWriter publicityWriter(MailSender mailSender) {
		return new SimpleMailMessageItemWriterBuilder()
				.mailSender(mailSender) //Spring will get the properties related to mail from the app properties because this is a Bean
				.build();
	}
	
}
