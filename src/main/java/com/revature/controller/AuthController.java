package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Roles;
import com.revature.model.User;
import com.revature.repository.UserRepositoryDAO;
import com.revature.repository.UserRepositoryDAOImpl;

import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

public class AuthController {
	
		
	 UserRepositoryDAO dao = new UserRepositoryDAOImpl();
	
	    public Handler authenticateReq = ctx -> 
		 {
			
			 String userName = ctx.formParam("userName");
             String password = ctx.formParam("password");
             
			  UserRepositoryDAO dao = new UserRepositoryDAOImpl();
	          User user = dao.findByUserNameAndPassword(userName,password);
	             
	             System.out.println(">>>user >>>"+user);
	             if (user==null) 
	             {
	            	 ctx.redirect("/Fail_Login.html");
	             } else {
	            	 ctx.sessionAttribute("currentUserName", user.getUserName());
	            	 ctx.sessionAttribute("currentUser", user);
	                
	                 Roles userRole = user.getRole();
	                 System.out.println(">>>user role>>>"+userRole.name());
	                 
	                 if(userRole.equals(Roles.Manager))
	                 {
	                	 ctx.redirect("/Manager.html");
	                 }else if(userRole.equals(Roles.Employee))
	                 {
	                	 ctx.header("Access-Control-Expose-Headers", "Current-User");
						 ctx.header("Current-User", "" + user.getId());
	                	 ctx.redirect("/Employee.html");
	                 }else
	                 {
	                	 ctx.redirect("/Error.html");
	                 }
	             }
			 
		};
	    
		  public Handler authenticateServ = ctx -> 
			{

				try {
					String userName = ctx.queryParam("userName");
					String password = ctx.queryParam("password");

					UserRepositoryDAO dao = new UserRepositoryDAOImpl();
					User user = dao.findByUserNameAndPassword(userName, password);

					System.out.println(">>>user >>>" + user);

					if (user == null) {
						ctx.status(HttpCode.BAD_REQUEST);
						ctx.result("Invalid Credentials");
					} else {
						ctx.sessionAttribute("currentUserName", user.getUserName());
		            	ctx.sessionAttribute("currentUser", user);
		            	 
		            	 
						ctx.status(HttpCode.ACCEPTED);
						ctx.header("Access-Control-Expose-Headers", "Current-User");
						ctx.header("Current-User", "" + user.getId());
						ctx.result(user.getRole().name());
					}
				} catch (Exception e) {
					// returning 500 status
					ctx.status(HttpCode.INTERNAL_SERVER_ERROR);

					// if exception has message, send it to response body
					if (!e.getMessage().isEmpty())
						ctx.result(e.getMessage());

					// Stack trace to help debug
					e.printStackTrace();
				}

			};
			  
			public Handler activeUserServ = ctx -> 
			 {
				User user = ctx.sessionAttribute("currentUser");
				System.out.println(">>>user >>>" + user);
				if(user==null)
				{
					ctx.result("No User Logged in. No active User");   
				}else
				{
					ctx.json(user);
				}
				
			}; 
			  
		  
		  public Handler registerReq = ctx -> 
		  {
				 String userName = ctx.formParam("user_name");
	             String password = ctx.formParam("password");
	             String userRole = ctx.formParam("user_role");
	            
	             User newUser = new User(0,userName, password,Roles.valueOf(userRole));
	             int userId = dao.createUser(newUser);
	         	
	             if (userId < 0) 
	             {
	            	 ctx.redirect("/Fail_Registration.html");	   
	             }else 
	             {
	            	 ctx.redirect("/Success_Registration.html");	   
	             }
			};
		  
		  public Handler registerServ = ctx -> 
		  {
				try
				{
					String input = ctx.body();
					
					ObjectMapper mapper = new ObjectMapper();
					User newUser = mapper.readValue(input, User.class);
					int userId = dao.createUser(newUser);
					
					if (userId < 0) 
		            {
						ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
		            	ctx.result("Registration Unsuccessful");   
		            } else 
		            {
		            	ctx.status(HttpCode.CREATED);
		            	ctx.result("Registration Successful");      
		            }
					
				}catch(Exception e)
				{
					//returning 500 status
					ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
					
					//if exception has message, send it to response body
					if(!e.getMessage().isEmpty())
						ctx.result(e.getMessage());   
					
					//Stack trace to help debug
					e.printStackTrace();
				}
			};
	    
		
			  
			  
}
