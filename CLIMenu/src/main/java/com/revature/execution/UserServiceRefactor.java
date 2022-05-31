package com.revature.execution;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.model.Roles;
import com.revature.model.User;
import com.revature.service.UserService;

public class UserServiceRefactor 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		// loading the definitions from the given XML file
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) 
		{
			UserService service = (UserService) context.getBean("userService");
			
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- CREATE NEW USER -----");
			System.out.println("---------------------------------------------------------------------");
			//To Create New User define User object
			//Here userId is set 0, but in DB it creates by sequence.
			//To avoid duplicate key exception for every run , added timestampflag
			service.create(new User(0,"TestUSER_"+new Timestamp(System.currentTimeMillis()).getTime(), "TEST@1234",Roles.Employee));
			service.create(new User(0,"TestMNGE_"+new Timestamp(System.currentTimeMillis()).getTime(), "TESTMng4",Roles.Manager));
			
			//You may get duplicate userName exception if you run this muplitiple times.
			service.create(new User(0,"TestKKKK", "TESTMffff4",Roles.Manager));
			
			
			//Get User by User Name
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- GET USER  BY NAME   -----");
			System.out.println("---------------------------------------------------------------------");
			User user = service.getUserByName("TestKKKK");
			printUser(user);
			
			
			//Get User by User ID
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- GET USER  BY ID   -----");
			System.out.println("---------------------------------------------------------------------");
			user = service.getUserByID(2);
			printUser(user);
			
			//Get all Users
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- GET ALL USERSLIST   -----");
			System.out.println("---------------------------------------------------------------------");
			List<User> usersList = service.getAllUsers();
			printUsersList(usersList);
			
			//Get Users by Role
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- GET USERSLIST BY ROLE [EMPLOYEE]   -----");
			System.out.println("---------------------------------------------------------------------");
			usersList.clear();
			usersList = service.getUsersByRole(Roles.valueOf("Employee"));
			printUsersList(usersList);
			
			
			//Check User Exist
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- CHECK USERS EXISTED OR NOT  -----");
			System.out.println("---------------------------------------------------------------------");
			boolean userExist = service.checkUserExists(2);
			System.out.println("Is User exists ?  " +userExist);
			
			
			
			
			
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
				

	}
	
	
	public static void printUser(User user)
	{
		if(user!=null)
		{
			System.out.println(">>>Id>>>"+user.getId());
			System.out.println(">>>Name>>>"+user.getUserName());
			System.out.println(">>>Password>>>"+user.getPassword());
			System.out.println(">>>Role>>>"+user.getRole());
			System.out.println("------------------------------------------------");
		}else
		{
			System.out.println(">>>USER NOT FOUND>>>");
		}
		
	}
	
	public static void printUsersList(List<User> usersList)
	{
		for(User user: usersList)
		{
			printUser(user);
		}
	}

}
