package com.revature.execution;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.model.Reimbursment;
import com.revature.model.Reimbursment_Type;
import com.revature.model.Status;
import com.revature.service.ReimbursementService;

public class ReimbursmentServiceRefactor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) 
		{
			ReimbursementService service = (ReimbursementService) context.getBean("reimbursementService");
			List<Reimbursment> reimbursmentsList = new ArrayList<>();
			
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- CREATE NEW Reimbursement  -----");
			System.out.println("---------------------------------------------------------------------");
			
			//To Create New Reimbursment define Reimbursment object
			//Here Id is set 0, but in DB it creates by sequence.
			//Here Resolver is set 0, but in DB we are not taking this while inserting it .
			service.submit(new Reimbursment(0, 1, 0, "Test Reimbursment Desc 1", Reimbursment_Type.Lodging, Status.Pending,8327.89));
			service.submit(new Reimbursment(0, 1, 0, "Test Reimbursment Desc 2", Reimbursment_Type.Food, Status.Pending,3546.89));
			service.submit(new Reimbursment(0, 1, 0, "Test Reimbursment Desc 3", Reimbursment_Type.Other, Status.Pending,634.89));
			service.submit(new Reimbursment(0, 3, 0, "Test Reimbursment Desc 4", Reimbursment_Type.Travel, Status.Pending,7323.89));
			service.submit(new Reimbursment(0, 2, 0, "Test Reimbursment Desc 5", Reimbursment_Type.Food, Status.Pending,1433.89));
			
			
			
			//Get Reimbursement by ID
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- GET Reimbursement BY ID   -----");
			System.out.println("---------------------------------------------------------------------");
			Reimbursment reimbursment = service.getReimbursementByID(2);
			printReimbursment(reimbursment);
			
			
			//Get Reimbursement by User - Author
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- GET Reimbursements BY User - Author   -----");
			System.out.println("---------------------------------------------------------------------");
			reimbursmentsList.clear();
			reimbursmentsList = service.getReimbursmentsByAuthor(2);
			printReimbursmentsList(reimbursmentsList);
			
			//Get Reimbursement by User - Resolver
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- GET Reimbursements BY User - Resolver   -----");
			System.out.println("---------------------------------------------------------------------");
			reimbursmentsList.clear();
			reimbursmentsList = service.getReimbursmentsByResolver(3);
			printReimbursmentsList(reimbursmentsList);
			
			
			//Get All Pending Reimbursements
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- GET Reimbursement BY Status [Pending]  -----");
			System.out.println("---------------------------------------------------------------------");
			reimbursmentsList.clear();
			reimbursmentsList = service.getPendingReimbursments();
			printReimbursmentsList(reimbursmentsList);
			
			//Get All Reimbursements
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- GET All Reimbursements  -----");
			System.out.println("---------------------------------------------------------------------");
			reimbursmentsList.clear();
			reimbursmentsList = service.getReimbursments();
			printReimbursmentsList(reimbursmentsList);
			
			//Update Existing Reimbursement
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- Update Reimbursement  -----");
			System.out.println("---------------------------------------------------------------------");
			service.update(1, 3, Status.Approved);
			service.update(2, 3, Status.Denied);
			
			//Get All Approved Reimbursements
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- GET Reimbursement BY Status [Approved]  -----");
			System.out.println("---------------------------------------------------------------------");
			reimbursmentsList.clear();
			reimbursmentsList = service.getApprovedReimbursments();
			printReimbursmentsList(reimbursmentsList);
			
			//Get All Denied Reimbursements
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- GET Reimbursement BY Status [Denied]  -----");
			System.out.println("---------------------------------------------------------------------");
			reimbursmentsList.clear();
			reimbursmentsList = service.getDeniedReimbursments();
			printReimbursmentsList(reimbursmentsList);
			
			
			
			//Get All Resolved Reimbursements
			System.out.println("---------------------------------------------------------------------");
			System.out.println("- GET Resolved Reimbursements [Approved  or Denied]  -----");
			System.out.println("---------------------------------------------------------------------");
			reimbursmentsList.clear();
			reimbursmentsList = service.getResolvedReimbursments();
			printReimbursmentsList(reimbursmentsList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void printReimbursment(Reimbursment reimbursment)
	{
		if(reimbursment!=null)
		{
			
			System.out.println(">>>Id>>>"+reimbursment.getId());
			System.out.println(">>>Author>>>"+reimbursment.getAuthor());
			System.out.println(">>>Resolver>>>"+reimbursment.getResolver());
			System.out.println(">>>Description>>>"+reimbursment.getDescription());
			System.out.println(">>>Type>>>"+reimbursment.getType().name());
			System.out.println(">>>Amount>>>"+reimbursment.getAmount());
			System.out.println(">>>Status>>>"+reimbursment.getStatus().name());
			System.out.println("------------------------------------------------");
		}else
		{
			System.out.println(">>>Reimbursment NOT FOUND>>>");
		}
		
	}
	
	public static void printReimbursmentsList(List<Reimbursment> reimbursmentsList)
	{
		for(Reimbursment reimbursment: reimbursmentsList)
		{
			printReimbursment(reimbursment);
		}
	}

}
