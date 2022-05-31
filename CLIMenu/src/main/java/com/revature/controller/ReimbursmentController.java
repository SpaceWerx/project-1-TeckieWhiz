package com.revature.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursment;
import com.revature.model.Reimbursment_Type;
import com.revature.model.Status;
import com.revature.repository.ReimbursementRepositoryDAO;
import com.revature.repository.ReimbursementRepositoryDAOImpl;

import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

public class ReimbursmentController {
	
	 ReimbursementRepositoryDAO dao = new ReimbursementRepositoryDAOImpl();
	 
	 
	  public Handler fetchByAuthor = ctx -> {
		  
	        int userId = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("author")));
	       
	        List<Reimbursment> rembList = dao.findAllReimbursmentsByAuthor(userId);
	        
	        if (rembList == null) {
	            ctx.html("Reimbursments Not Found");
	        } else {
	            ctx.json(rembList);
	        }
	    };
	    
	    public Handler getAll = ctx -> {
			
	        List<Reimbursment> rembList = dao.findAllReimbursments();
	        
	        if (rembList == null) {
	            ctx.html("Reimbursment Not Found");
	        } else {
	            ctx.json(rembList);
	        }
	    };
	   
	    public Handler addReimbursmentReq = ctx -> 
		  {
			  	int resolver = 0;
	         	int id = 0;
	         	
	         	
				int author = Integer.parseInt(ctx.formParam("author"));
	         	String description = ctx.formParam("description");
	         	Reimbursment_Type type = Reimbursment_Type.valueOf(ctx.formParam("typeInput"));
	         	double amount = Double.parseDouble(ctx.formParam("amount"));
	         	
	         	
	         	Reimbursment newReimbursment = new Reimbursment(id, author, resolver, description, type, Status.Pending,amount);
				
	             int reimbursmentId = dao.save(newReimbursment);
	         		
	             HashMap<String,String> submitStatus = new HashMap<>();
	             submitStatus.put("id",""+reimbursmentId);
	             if (reimbursmentId < 0) 
	             {
	            	 submitStatus.put("status","Failed");
	            	 ctx.json(submitStatus);   
	             }else 
	             {
	            	 submitStatus.put("status","Success");
	            	 ctx.json(submitStatus);   
	             }
			};  
			
			
			
			 public Handler addReimbursment = ctx -> 
			  {
				  try
					{
						String input = ctx.body();
						
						System.out.println(">>>input >>>" + input);
						
						ObjectMapper mapper = new ObjectMapper();
						Reimbursment newReimbursment = mapper.readValue(input, Reimbursment.class);
						
						 int reimbursmentId = dao.save(newReimbursment);
			         		
			             HashMap<String,String> submitStatus = new HashMap<>();
			             submitStatus.put("id",""+reimbursmentId);
			             
			             if (reimbursmentId < 0) 
				            {
								ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
				            	ctx.result("FAILED: Reimbursment Submit Unsuccessful");   
				            } else 
				            {
				            	ctx.status(HttpCode.CREATED);
				            	ctx.result("SUCCESS: Reimbursment Submitted Successfully");      
				            }
			       	
					}catch(Exception e)
					{
						//returning 500 status
						ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
						
						//if exception has message, send it to response body
						if(!e.getMessage().isEmpty())
							ctx.result(e.getMessage());   
						
						//Stack trace to help debug
						e.printStackTrace();
					}
				};  
				
				
	    
				 public Handler updateReimbursmentReq = ctx -> 
				  {
					   	int id = Integer.parseInt(ctx.formParam("id"));
						int resolver = Integer.parseInt(ctx.formParam("resolver"));
						
			         	Status currentStatus = Status.valueOf(ctx.formParam("currStatus"));
			         	Status status = Status.valueOf(ctx.formParam("status"));
			         	
			         	 HashMap<String,String> submitStatus = new HashMap<>();
			             submitStatus.put("id",""+id);
			             submitStatus.put("status",currentStatus.name()+" to "+status.name());
			             
			             
			         	
			         	if(currentStatus.name().toString().equals(Status.Denied.name().toString()))
			         	{
			         		submitStatus.put("UpdateStatus","Reimbursment is already Denied. No further change!!!");
			         		 
			         	}else if(currentStatus.name().equals(Status.Approved.name()))
			         	{
			         		submitStatus.put("UpdateStatus","Reimbursment is already Approved. No further change!!!");
			         	}else
			         	{
			         		Reimbursment newReimbursment = new Reimbursment(id, 0, resolver, null, null, status,0);
				             if (dao.update(newReimbursment)) 
				             {
				            	 submitStatus.put("UpdateStatus","Success");
				             }else 
				             {
				            	 submitStatus.put("UpdateStatus","FAILED");
				             }
			         	}
			         	
			         	ctx.json(submitStatus);  
			         	
			         	
			         	
					};  
					
					 public Handler updateReimbursment = ctx -> 
					  {
						  try
							{
								String input = ctx.body();
								
								System.out.println(">>>input >>>" + input);
								
								ObjectMapper mapper = new ObjectMapper();
								Reimbursment updatedReimbursment = mapper.readValue(input, Reimbursment.class);
								
								Reimbursment existingReimbursment = dao.findByReimbursmentByID(updatedReimbursment.getId());
								
								Status currentStatus = existingReimbursment.getStatus();
					         	Status status = updatedReimbursment.getStatus();
								 
					         	System.out.println(">>>currentStatus >>>" + currentStatus.name());
					         	System.out.println(">>>status >>>" + status.name());
					         	
					         	HashMap<String,String> submitStatus = new HashMap<>();
					            submitStatus.put("id",""+updatedReimbursment.getId());
					            submitStatus.put("status",currentStatus.name()+" to "+status.name());
					             
					         	if(currentStatus.name().toString().equals(Status.Denied.name().toString()))
					         	{
					         		submitStatus.put("UpdateStatus","Reimbursment is already Denied. No further change!!!");
					         		 
					         	}else if(currentStatus.name().equals(Status.Approved.name()))
					         	{
					         		submitStatus.put("UpdateStatus","Reimbursment is already Approved. No further change!!!");
					         	}else
					         	{
					         		
						             if (dao.update(updatedReimbursment)) 
						             {
						            	 submitStatus.put("UpdateStatus","Success");
						             }else 
						             {
						            	 submitStatus.put("UpdateStatus","FAILED");
						             }
					         	}
					         	ctx.json(submitStatus);
							}catch(Exception e)
							{
								//returning 500 status
								ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
								
								//if exception has message, send it to response body
								if(!e.getMessage().isEmpty())
									ctx.result(e.getMessage());   
								
								//Stack trace to help debug
								e.printStackTrace();
							}
						};  

}
