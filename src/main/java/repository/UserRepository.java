package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.User;

public interface UserRepository extends JpaRepository<User, Integer> 
{
    User findByUserName(String userName);
    User findByUserId(int id);
    
    List<User> findByUserByRole(String role);
    
    
}
