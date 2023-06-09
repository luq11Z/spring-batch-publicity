package com.lucaslearning.publicity.readers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.lucaslearning.publicity.domain.Client;
import com.lucaslearning.publicity.domain.ClientProductInterest;
import com.lucaslearning.publicity.domain.Product;

@Configuration
public class PublicityReaderConfig {

	@Bean
	public JdbcCursorItemReader<ClientProductInterest> publicityReader(@Qualifier("appDataSource") DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<ClientProductInterest>()
				.name("publicityReader")
				.dataSource(dataSource)
				.sql("SELECT * FROM client_product_interest "
						+ "JOIN client ON (client = client.id) "
						+ "JOIN product ON (product = product.id)")
				.rowMapper(rowMapper())
				.build();
	}

	private RowMapper<ClientProductInterest> rowMapper() {
		return new RowMapper<ClientProductInterest>() {
			@Override
			public ClientProductInterest mapRow(ResultSet rs, int rowNum) throws SQLException {
				Client client = new Client();
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setEmail(rs.getString("email"));
				
				Product product = new Product();
				product.setId(rs.getInt(6));
				product.setName(rs.getString(7));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getDouble("price"));
				
				ClientProductInterest clientProductInterest = new ClientProductInterest();
				clientProductInterest.setClient(client);
				clientProductInterest.setProduct(product);
				
				return clientProductInterest;
			}
		};
	}
	
}
