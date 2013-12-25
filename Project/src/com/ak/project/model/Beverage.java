package com.ak.project.model;

import java.io.Serializable;

public class Beverage implements Serializable{

	private String id;
	private String name;
	private String type;
	private double price;
	private int amount = 0;
	
	public Beverage(String id,String name,String type,double price){
		setName(name);
		setPrice(price);
		setId(id);
		setType(type);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int aantal) {
		this.amount = aantal;
	}
	
	public void increaseAmount(int number){
		amount+=number;
	}
	public void decreaseAmount(int number){
		amount-=number;
	}
}
