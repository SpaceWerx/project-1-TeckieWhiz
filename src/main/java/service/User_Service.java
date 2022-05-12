package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.User;
import repository.UserRepository;

@Controller
public class User_Service 
{
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/UserByName")
    public User getUserByName(@RequestParam (name="userName", required = true) String userName)
   	{
		return userRepo.findByUserName(userName);
    }
	
	@GetMapping("/UserById")
    public User getUserByID(@RequestParam (name="userId", required = true) int userId)
   	{
		return userRepo.findByUserId(userId);
    }
	
	
	@GetMapping("/Users")
    public List<User> getAllUser()
   	{
		return userRepo.findAll();
    }
	
	@GetMapping("/UserByRole")
    public List<User> getUsersByRole(@RequestParam (name="userRole", required = true) String userRole)
   	{
		return userRepo.findByUserByRole(userRole);
    }
	
	
}
