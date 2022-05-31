package com.revature.model;

public class Reimbursment 
{
	
	private int id;
	private int author;
	private int resolver;
	private String description;
	private Reimbursment_Type type;
	private Status status;
	private double amount;
	
	
	public Reimbursment(int id, int author, int resolver, String description, Reimbursment_Type type, Status status,
			double amount) {
		super();
		this.id = id;
		this.author = author;
		this.resolver = resolver;
		this.description = description;
		this.type = type;
		this.status = status;
		this.amount = amount;
	}

	
		
	public Reimbursment()
	{
		super();
	}



	public int getId() {
		return id;
	}



	public int getAuthor() {
		return author;
	}



	public int getResolver() {
		return resolver;
	}



	public String getDescription() {
		return description;
	}



	public Reimbursment_Type getType() {
		return type;
	}



	public Status getStatus() {
		return status;
	}



	public double getAmount() {
		return amount;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setAuthor(int author) {
		this.author = author;
	}



	public void setResolver(int resolver) {
		this.resolver = resolver;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public void setType(Reimbursment_Type type) {
		this.type = type;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}

}
