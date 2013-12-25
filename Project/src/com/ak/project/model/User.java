package com.ak.project.model;

import java.io.Serializable;

public class User implements Serializable{

	private String name;
	private String surname;
	private String password;
	private String email;
	private double saldo;

	public User(String name, String surname, String password, String email,
			double saldo) {
		setName(name);
		setSurname(surname);
		setSaldo(saldo);
		setPassword(password);
		setEmail(email);
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
