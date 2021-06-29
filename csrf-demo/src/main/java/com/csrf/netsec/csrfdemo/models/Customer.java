package com.csrf.netsec.csrfdemo.models;

public class Customer {
	private int balance;
	private String name;
	
	public Customer() {
		this.balance = 1000;
		this.name = "John";
	}
	
	public Customer(String name, int balance) {
		this.name = name;
		this.balance = balance;
	}

	public int getBalance() {
		return this.balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void removeMoney(int amount) {
		this.balance -= amount;
	}
}
