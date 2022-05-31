package com.revature.repository;

import java.util.List;

import com.revature.model.Roles;
import com.revature.model.User;

public interface UserRepositoryDAO
{
	//Get User byID
    User findByUserId(int id);
    
	//Get User by name
    User findByUserName(String userName);
    
    //Get All Users
    List<User> findAll();
    
    //Get Users list by Role
    List<User> findUsersByRole(Roles userRole);
    
    //Create new User
    int createUser(User user);
    
    //Check User credentials to Login
    User findByUserNameAndPassword(String userName, String password);
   
}
