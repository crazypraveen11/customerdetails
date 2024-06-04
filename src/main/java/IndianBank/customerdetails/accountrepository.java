package IndianBank.customerdetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
   
@Repository
public interface accountrepository extends JpaRepository<accountentity,Long>
{
   public List<accountentity> findByAccountHolderplace(String place);

   public accountentity findByAccountHoldername(String name);
    
} 
