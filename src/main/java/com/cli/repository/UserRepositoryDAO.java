package com.cli.repository;

import java.util.List;

import com.cli.model.Roles;
import com.cli.model.User;

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
   
}
