package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.model.Reimbursment;
import com.revature.model.Status;
import com.revature.repository.ReimbursementRepositoryDAO;
import com.revature.repository.ReimbursementRepositoryDAOImpl;

@Service("reimbursementService")
public class ReimbursementService 
{
	
	
	ReimbursementRepositoryDAO reimbursementRepository = new ReimbursementRepositoryDAOImpl();
	
	public Reimbursment getReimbursementByID(int Id)
   	{
		return reimbursementRepository.findByReimbursmentByID(Id);
    }
	
   
	 public List<Reimbursment> getReimbursments()
	   	{
	    	return reimbursementRepository.findAllReimbursments();
	    }
	 
    public List<Reimbursment> getResolvedReimbursments()
   	{
    	List<Reimbursment> resolvedReimbursments = new ArrayList<>();
    	List<Reimbursment> approvedReimbursments = reimbursementRepository.findAllReimbursmentsByStatus(Status.Approved);
    	List<Reimbursment> deniedReimbursments = reimbursementRepository.findAllReimbursmentsByStatus(Status.Denied);
    	resolvedReimbursments.addAll(approvedReimbursments);
    	resolvedReimbursments.addAll(deniedReimbursments);
		return resolvedReimbursments;
    }
    
    public List<Reimbursment> getPendingReimbursments()
   	{
    	return reimbursementRepository.findAllReimbursmentsByStatus(Status.Pending);
    }
    
    public List<Reimbursment> getApprovedReimbursments()
   	{
    	return reimbursementRepository.findAllReimbursmentsByStatus(Status.Approved);
    }
    
    public List<Reimbursment> getDeniedReimbursments()
   	{
    	return reimbursementRepository.findAllReimbursmentsByStatus(Status.Denied);
    }
	
    public List<Reimbursment> getReimbursmentsByAuthor(int userId)
   	{
		return reimbursementRepository.findAllReimbursmentsByAuthor(userId);
    }
    
    public List<Reimbursment> getReimbursmentsByResolver(int userId)
   	{
		return reimbursementRepository.findAllReimbursmentsByResolver(userId);
    }
	
	
    public Reimbursment submit(Reimbursment submittedReimbursment)
   	{
    	//ID is Auto increment, So no need to set ID.
    	submittedReimbursment.setStatus(Status.Pending);
    	int remId = reimbursementRepository.save(submittedReimbursment);
    	return reimbursementRepository.findByReimbursmentByID(remId);
    	
    }
    
    public Reimbursment update(int reimbursmentId, int resolverID, Status updatedStatus)
   	{
    	    	
		Reimbursment existingReimbursment = reimbursementRepository.findByReimbursmentByID(reimbursmentId);
		
		if(existingReimbursment!=null)
		{
			Status status = existingReimbursment.getStatus();
			
			if(status.name().equalsIgnoreCase(Status.Pending.name()))
			{
				existingReimbursment.setResolver(resolverID);
				existingReimbursment.setStatus(updatedStatus);
				reimbursementRepository.update(existingReimbursment);
			}else
			{
				System.out.println("NO UPDATE: Reimbursment CANNOT BE "+updatedStatus.name()+"... It is already ."+status.name());
			}
			
			
		}else
		{
			System.out.println("Reimbursment Not Found");
			throw new RuntimeException(" ERROR while updating Reimbursment.");
		}
		return existingReimbursment;
    }
	
}
