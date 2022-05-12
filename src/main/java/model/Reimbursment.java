package model;

public class Reimbursment 
{
	
	private int id;
	private int author;
	private int resolver;
	private String description;
	private Reimbursment_Type type;
	private Status status;
	private double amount;
	
	public Reimbursment() {
		super();
	}
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Reimbursment_Type getType() {
		return type;
	}

	public void setType(Reimbursment_Type type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
		
}
	