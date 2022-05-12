package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import repository.ReimbursementRepository;

import model.Reimbursment;

@Controller
public class Reimbursement_Service 
{
	
	@Autowired
	ReimbursementRepository reimbursementRepository;
	
		
	@GetMapping("/ReimbursementById")
    public Reimbursment getReimbursementByID(@RequestParam (name="Id", required = true) int Id)
   	{
		return reimbursementRepository.findByReimbursmentByID(Id);
    }
	
	
	@GetMapping("/PendingReimbursements")
    public List<Reimbursment> getAllPending()
   	{
		return reimbursementRepository.findAllPendingReimbursments();
    }
	
	@GetMapping("/ResolvedReimbursements")
    public List<Reimbursment> getAllResolved()
   	{
		return reimbursementRepository.findAllReolvedReimbursments();
    }
	
	@GetMapping("/ReimbursementsByUser")
    public List<Reimbursment> getAllByUser(@RequestParam (name="UserId", required = true) int userId)
   	{
		return reimbursementRepository.findAllReimbursmentsByUser(userId);
    }
	
	
	@GetMapping("/Save")
    public Reimbursment submit(@RequestBody Reimbursment reimbursment)
   	{
		return reimbursementRepository.save(reimbursment);
    }
	
	@GetMapping("/Update")
    public Reimbursment update(@RequestBody Reimbursment reimbursment,@PathVariable ("id") int id)
   	{
		Reimbursment existingRem = reimbursementRepository.findByReimbursmentByID(id);
		
		existingRem.setAmount(reimbursment.getAmount());
		existingRem.setAuthor(reimbursment.getAuthor());
		existingRem.setDescription(reimbursment.getDescription());
		existingRem.setResolver(reimbursment.getResolver());
		existingRem.setStatus(reimbursment.getStatus());
		existingRem.setType(reimbursment.getType());
		
		return reimbursementRepository.save(reimbursment);
    }
	
}
