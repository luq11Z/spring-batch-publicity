package com.lucaslearning.publicity.processors;

import java.text.NumberFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.lucaslearning.publicity.domain.ClientProductInterest;

@Component
public class PublicityProcessor implements ItemProcessor<ClientProductInterest, SimpleMailMessage>{

	@Override
	public SimpleMailMessage process(ClientProductInterest item) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("xpto@no-reply.com");
		message.setTo(item.getClient().getEmail());
		message.setSubject("Incridible discount!!!");
		message.setText(publicityText(item));
		
		Thread.sleep(3000);
		
		return message;
	}

	private String publicityText(ClientProductInterest item) {
		StringBuilder writer = new StringBuilder();
		
		writer.append(String.format("Hello, %s!\n\n", item.getClient().getName()));
		writer.append("This discount might be exactly what you are looking for:\n\n");
		writer.append(String.format("%s - %s\n\n", item.getProduct().getName(), item.getProduct().getDescription()));
		writer.append(String.format("Just: %s", 
				NumberFormat.getCurrencyInstance().format(item.getProduct().getPrice())));
		
		return writer.toString();
	}

	

}
