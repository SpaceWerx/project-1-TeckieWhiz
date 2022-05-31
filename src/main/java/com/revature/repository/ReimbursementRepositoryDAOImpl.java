package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.database.ConnectionFactoryUtil;
import com.revature.model.Reimbursment;
import com.revature.model.Reimbursment_Type;
import com.revature.model.Status;

public class ReimbursementRepositoryDAOImpl implements ReimbursementRepositoryDAO
{
	Connection conn = ConnectionFactoryUtil.getConnection();
	PreparedStatement stmt;
	
	@Override
	public Reimbursment findByReimbursmentByID(int id) {
		String sql="SELECT * FROM ERS_REIMBURSMENT WHERE ID = ?";
		try 
		{
			if(conn == null || conn.isClosed())
              	conn = ConnectionFactoryUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) 
			{
				return new Reimbursment(rs.getInt("ID"), 
						rs.getInt("AUTHOR"), 
						rs.getInt("RESOLVER"),
						rs.getString("DESCRIPTION"), 
						Reimbursment_Type.valueOf(rs.getString("TYPE")),
						Status.valueOf(rs.getString("STATUS")),
						rs.getDouble("AMOUNT")) ;
			}
		}catch(SQLException e) {
			System.out.println("Something went wrong in PersonDaoPostgres");
			e.printStackTrace();
		}finally
		{
			closeConnection();
		}
		return null;
	}

	@Override
	public List<Reimbursment> findAllReimbursmentsByStatus(Status status) 
	{
		String sql="SELECT * FROM ERS_REIMBURSMENT WHERE STATUS = ?";
		List<Reimbursment> reimbursments = new ArrayList<>();
		try 
		{
			if(conn == null || conn.isClosed())
              	conn = ConnectionFactoryUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setObject(1, status, Types.OTHER);
			
			
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) 
			{
				reimbursments.add(new Reimbursment(rs.getInt("ID"), 
						rs.getInt("AUTHOR"), 
						rs.getInt("RESOLVER"),
						rs.getString("DESCRIPTION"), 
						Reimbursment_Type.valueOf(rs.getString("TYPE")),
						Status.valueOf(rs.getString("STATUS")),
						rs.getDouble("AMOUNT"))) ;
			}
		}catch(SQLException e) {
			System.out.println("Something went wrong in ReimbursementRepositoryDAO Postgres");
			e.printStackTrace();
		}finally
		{
			closeConnection();
		}
		return reimbursments;
	}

	
	@Override
	public List<Reimbursment> findAllReimbursmentsByAuthor(int id) {
		String sql="SELECT * FROM ERS_REIMBURSMENT WHERE AUTHOR = ?";
		List<Reimbursment> reimbursments = new ArrayList<>();
		try 
		{
			if(conn == null || conn.isClosed())
              	conn = ConnectionFactoryUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) 
			{
				reimbursments.add(new Reimbursment(rs.getInt("ID"), 
						rs.getInt("AUTHOR"), 
						rs.getInt("RESOLVER"),
						rs.getString("DESCRIPTION"), 
						Reimbursment_Type.valueOf(rs.getString("TYPE")),
						Status.valueOf(rs.getString("STATUS")),
						rs.getDouble("AMOUNT"))) ;
			}
		}catch(SQLException e) {
			System.out.println("Something went wrong in ReimbursementRepositoryDAO Postgres");
			e.printStackTrace();
		}finally
		{
			closeConnection();
		}
		return reimbursments;
	}
	
	
	@Override
	public List<Reimbursment> findAllReimbursmentsByResolver(int id) {
		String sql="SELECT * FROM ERS_REIMBURSMENT WHERE RESOLVER = ?";
		List<Reimbursment> reimbursments = new ArrayList<>();
		try 
		{
			if(conn == null || conn.isClosed())
              	conn = ConnectionFactoryUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) 
			{
				reimbursments.add(new Reimbursment(rs.getInt("ID"), 
						rs.getInt("AUTHOR"), 
						rs.getInt("RESOLVER"),
						rs.getString("DESCRIPTION"), 
						Reimbursment_Type.valueOf(rs.getString("TYPE")),
						Status.valueOf(rs.getString("STATUS")),
						rs.getDouble("AMOUNT"))) ;
			}
		}catch(SQLException e) {
			System.out.println("Something went wrong in ReimbursementRepositoryDAO Postgres");
			e.printStackTrace();
		}finally
		{
			closeConnection();
		}
		return reimbursments;
	}

	@Override
	public int save(Reimbursment submittedReimbursment) 
	{
		String sql="INSERT INTO ERS_REIMBURSMENT (AUTHOR, DESCRIPTION, TYPE, STATUS, AMOUNT)"
				+ "	VALUES (?, ?, ?, ?, ?) RETURNING ERS_REIMBURSMENT.ID";
		try 
		{
			if(conn == null || conn.isClosed())
              	conn = ConnectionFactoryUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, submittedReimbursment.getAuthor());
			stmt.setString(2, submittedReimbursment.getDescription());
			stmt.setObject(3, submittedReimbursment.getType(), Types.OTHER);
			stmt.setObject(4, submittedReimbursment.getStatus(), Types.OTHER);
			stmt.setDouble(5, submittedReimbursment.getAmount());
			
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
	
	
	@Override
	public boolean update(Reimbursment updatedReimbursment) {
		String sql="UPDATE ERS_REIMBURSMENT SET RESOLVER=?,  STATUS=? WHERE ID=? ";
		try 
		{
			if(conn == null || conn.isClosed())
              	conn = ConnectionFactoryUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, updatedReimbursment.getResolver());
			stmt.setObject(2, updatedReimbursment.getStatus(), Types.OTHER);
			stmt.setInt(3, updatedReimbursment.getId());
			
			stmt.executeUpdate();
			
			return true;
			
		}catch(SQLException e) {
			System.out.println("Something went wrong in ReimbursementRepositoryDAO Postgres");
			e.printStackTrace();
			return false;
		}finally
		{
			closeConnection();
		}
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
	public List<Reimbursment> findAllReimbursments() {
		String sql="SELECT * FROM ERS_REIMBURSMENT";
		List<Reimbursment> reimbursments = new ArrayList<>();
		try 
		{
			if(conn == null || conn.isClosed())
              	conn = ConnectionFactoryUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) 
			{
				reimbursments.add(new Reimbursment(rs.getInt("ID"), 
						rs.getInt("AUTHOR"), 
						rs.getInt("RESOLVER"),
						rs.getString("DESCRIPTION"), 
						Reimbursment_Type.valueOf(rs.getString("TYPE")),
						Status.valueOf(rs.getString("STATUS")),
						rs.getDouble("AMOUNT"))) ;
			}
		}catch(SQLException e) {
			System.out.println("Something went wrong in ReimbursementRepositoryDAO Postgres");
			e.printStackTrace();
		}finally
		{
			closeConnection();
		}
		return reimbursments;
	}
	
	

}
