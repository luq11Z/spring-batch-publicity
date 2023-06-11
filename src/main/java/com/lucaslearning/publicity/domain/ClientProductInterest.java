package com.lucaslearning.publicity.domain;

public class ClientProductInterest {

	private Client client;
	private Product product;

	public ClientProductInterest() {

	}

	public ClientProductInterest(Client client, Product product) {
		this.client = client;
		this.product = product;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
