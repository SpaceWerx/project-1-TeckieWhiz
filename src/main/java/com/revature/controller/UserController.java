package com.revature.controller;

import java.util.Objects;

import com.revature.model.Roles;
import com.revature.model.User;
import com.revature.repository.UserRepositoryDAO;
import com.revature.repository.UserRepositoryDAOImpl;
import com.revature.service.UserService;

import io.javalin.http.Handler;

public class UserController {
	
	
	 public static Handler fetchAllUsernames = ctx -> 
	 {
		 	UserRepositoryDAO dao = new UserRepositoryDAOImpl();
	        Iterable<User> allUsers = dao.findAll();
	        ctx.json(allUsers);
	  };
	  
	  public static Handler fetchById = ctx -> {
	        int userId = Integer.parseInt(Objects.requireNonNull(ctx.pathParam("id")));
	        UserRepositoryDAO dao = new UserRepositoryDAOImpl();
	        User user = dao.findByUserId(userId);
	        if (user == null) {
	            ctx.html("User Not Found");
	        } else {
	            ctx.json(user);
	        }
	    };
	   
	    public static Handler fetchByName = ctx -> {
	        String userName = ctx.pathParam("userName");
	        UserRepositoryDAO dao = new UserRepositoryDAOImpl();
	        User user = dao.findByUserName(userName);
	        if (user == null) {
	            ctx.html("User Not Found");
	        } else {
	            ctx.json(user);
	        }
	    };   
	    
	    

}
