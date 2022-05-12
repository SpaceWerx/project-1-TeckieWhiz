package model;

public class Reimbursment 
{
	
	private int id;
	private int Author;
	private int Resolver;
	private String Description;
	private Reimbursment_Type type;
	private Status status;
	private double Amount;
		
	public Reimbursment()
	{
		super();
	}

	public Reimbursment(int id, int author, int resolver, String description, Reimbursment_Type type, Status status,
			double amount) {
		super();
		this.id = id;
		Author = author;
		Resolver = resolver;
		Description = description;
		this.type = type;
		this.status = status;
		Amount = amount;
	}

	public int getId() {
		return id;
	}

	public int getAuthor() {
		return Author;
	}

	public int getResolver() {
		return Resolver;
	}

	public String getDescription() {
		return Description;
	}

	public Reimbursment_Type getType() {
		return type;
	}

	public Status getStatus() {
		return status;
	}

	public double getAmount() {
		return Amount;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAuthor(int author) {
		Author = author;
	}

	public void setResolver(int resolver) {
		Resolver = resolver;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public void setType(Reimbursment_Type type) {
		this.type = type;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}
	    
  
}
