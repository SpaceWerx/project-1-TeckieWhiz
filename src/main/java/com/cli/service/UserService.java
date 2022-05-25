package com.cli.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cli.model.Roles;
import com.cli.model.User;
import com.cli.repository.UserRepositoryDAO;
import com.cli.repository.UserRepositoryDAOImpl;

@Service("userService")
public class UserService
{

	UserRepositoryDAO userRepo = new UserRepositoryDAOImpl();
		
    public User getUserByName(String userName)
   	{
		return userRepo.findByUserName(userName);
    }
	
	
    public User getUserByID(int userId)
   	{
		return userRepo.findByUserId(userId);
    }
	
	
    public List<User> getAllUsers()
   	{
		return userRepo.findAll();
    }
	

    public List<User> getUsersByRole(Roles userRole)
   	{
		return userRepo.findUsersByRole(userRole);
    }
	
    
    public boolean checkUserExists(int userId)
   	{
		User user = userRepo.findByUserId(userId);
    	if(user!=null)
    		return true;
    	else
    		return false;
    }
    
    public User create(User submittedUser)
   	{
    	//ID is Auto increment, So no need to set ID.
    	int userId = userRepo.createUser(submittedUser);
    	return userRepo.findByUserId(userId);
    	
    }
    
	
}
