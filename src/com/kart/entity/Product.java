package com.kart.entity;

import java.io.Serializable;

public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int price;
	String name;
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Product(int price, String name) {
		super();
		this.price = price;
		this.name = name;
	}
	
}

