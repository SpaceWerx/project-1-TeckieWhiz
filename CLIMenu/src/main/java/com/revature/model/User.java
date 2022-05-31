package com.revature.model;

public class User 
{
	
	private int Id;
	private String userName;
	private String password;
	private Roles role;
	
	public User()
	{
		super();
	}
	    
    public User(int id, String userName, String password,Roles role ) {
		super();
		this.Id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}


	public int getId() {
		return Id;
	}


	public String getUserName() {
		return userName;
	}


	public String getPassword() {
		return password;
	}


	public Roles getRole() {
		return role;
	}


	public void setId(int id) {
		Id = id;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setRole(Roles role) {
		this.role = role;
	}

}
