package com.cli.repository;

import java.util.List;

import com.cli.model.Reimbursment;
import com.cli.model.Status;

public interface ReimbursementRepositoryDAO 
{
	//Get Reimbursement by ID
	Reimbursment findByReimbursmentByID(int id);
    
	//Get Reimbursement by User - Author
    List<Reimbursment> findAllReimbursmentsByAuthor(int id);
    
    //Get Reimbursement by User - Resolver
    List<Reimbursment> findAllReimbursmentsByResolver(int id);
    
   //Get Reimbursement by Status
    List<Reimbursment> findAllReimbursmentsByStatus(Status status);
    
    //Get All Reimbursements
    List<Reimbursment> findAllReimbursments();
    
    //Create new Reimbursement
    int save(Reimbursment submittedReimbursment);
    
  //Update existing Reimbursement
    boolean update(Reimbursment updateReimbursment);
    
}
