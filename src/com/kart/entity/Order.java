package com.kart.entity;

import java.util.Vector;

public class Order {

	Vector<Product> products;
	int total;
	
	public Vector<Product> getProducts() {
		return products;
	}
	
	public void setProducts(Vector<Product> products) {
		this.products = products;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}

	public Order(Vector<Product> products, int total) {
		super();
		this.products = products;
		this.total = total;
	}
		
}
