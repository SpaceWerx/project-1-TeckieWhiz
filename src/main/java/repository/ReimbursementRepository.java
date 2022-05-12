package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Reimbursment;

public interface ReimbursementRepository extends JpaRepository<Reimbursment, Integer> 
{
	Reimbursment findByReimbursmentByID(int id);
    List<Reimbursment> findAllPendingReimbursments();
    List<Reimbursment> findAllReolvedReimbursments();
    List<Reimbursment> findAllReimbursmentsByUser(int id);
    
}
