package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.database.ConnectionFactoryUtil;
import com.revature.model.Roles;
import com.revature.model.User;

public class UserRepositoryDAOImpl implements UserRepositoryDAO
{
	Connection conn = ConnectionFactoryUtil.getConnection();
	PreparedStatement stmt;
	
	
	@Override
	public User findByUserName(String userName) 
	{
		String sql="SELECT * FROM ERS_USERS WHERE USERNAME = ?";
		try 
		{
			if(conn == null || conn.isClosed())
              	conn = ConnectionFactoryUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) 
			{
				return new User(rs.getInt("USERID"), rs.getString("USERNAME"), rs.getString("PASSWORD"),Roles.valueOf(rs.getString("ROLE"))) ;
			}
		}catch(SQLException e) {
			System.out.println("Something went wrong in UserRepositoryDAO");
			e.printStackTrace();
		}finally
		{
			closeConnection();
		}
		return null;
	}
	
	
	

	@Override
	public User findByUserId(int id) 
	{
		String sql="SELECT * FROM ERS_USERS WHERE USERID = ?";
		try 
		{
			if(conn == null || conn.isClosed())
              	conn = ConnectionFactoryUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) 
			{
				return new User(rs.getInt("USERID"), rs.getString("USERNAME"), rs.getString("PASSWORD"),Roles.valueOf(rs.getString("ROLE"))) ;
			}
		}catch(SQLException e) {
			System.out.println("Something went wrong in UserRepositoryDAO");
			e.printStackTrace();
		}finally
		{
			closeConnection();
		}
		return null;
	}

	@Override
	public List<User> findUsersByRole(Roles userRole) 
	{
		String sql="SELECT * FROM ERS_USERS WHERE ROLE = ?";
		List<User> users = new ArrayList<>();
		
		try 
		{
			if(conn == null || conn.isClosed())
              	conn = ConnectionFactoryUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setObject(1, userRole, Types.OTHER);
			
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) 
			{
				users.add(new User(rs.getInt("USERID"), rs.getString("USERNAME"), rs.getString("PASSWORD"),Roles.valueOf(rs.getString("ROLE")))) ;
			}
		}catch(SQLException e) {
			System.out.println("Something went wrong in UserRepositoryDAO ");
			e.printStackTrace();
		}finally
		{
			closeConnection();
		}
		
		return users;
	}

	@Override
	public List<User> findAll() {
		String sql="SELECT * FROM ERS_USERS";
		List<User> users = new ArrayList<>();
		
		try 
		{
			if(conn == null || conn.isClosed())
              	conn = ConnectionFactoryUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) 
			{
				users.add(new User(rs.getInt("ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"),Roles.valueOf(rs.getString("ROLE")))) ;
			}
		}catch(SQLException e) {
			System.out.println("Something went wrong in ReimbursementRepositoryDAO");
			e.printStackTrace();
		}finally
		{
			closeConnection();
		}
		
		return users;
	}




	@Override
	public int createUser(User user) {
		String sql="INSERT INTO ERS_USERS (USERNAME, PASSWORD, ROLE)"
				+ "	VALUES (?, ?, ?) RETURNING ERS_USERS.ID";
		try 
		{
			if(conn == null || conn.isClosed())
              	conn = ConnectionFactoryUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setObject(3, user.getRole(), Types.OTHER);

			ResultSet rs= stmt.executeQuery();
			
			if(rs !=null)
			{
				if(rs.next()) 
					return rs.getInt(1);
			}
			
		}catch(SQLException e) {
			System.out.println("Something went wrong in ReimbursementRepositoryDAO Postgres");
			e.printStackTrace();
		}finally
		{
			closeConnection();
		}
		
		return -1;
	}
	
	public void closeConnection()
	{
		try 
		{
			if(stmt!=null)
				stmt.close();
			
			if(conn!=null)
				conn.close();  
			
		} catch (SQLException e) {
			System.out.println("Error on Closing Connection");
			e.printStackTrace();
		}  
	}




	@Override
	public User findByUserNameAndPassword(String userName, String password) {
		String sql="SELECT * FROM ERS_USERS WHERE USERNAME = ? AND PASSWORD = ?";
		
		
		
		try 
		{
			if(conn == null || conn.isClosed())
              	conn = ConnectionFactoryUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, password);
			
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) 
			{
				return new User(rs.getInt("ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"),Roles.valueOf(rs.getString("ROLE"))) ;
			}
		}catch(SQLException e) {
			System.out.println("Something went wrong in UserRepositoryDAO");
			e.printStackTrace();
		}finally
		{
			closeConnection();
		}
		return null;
	}
   
    
    
}
