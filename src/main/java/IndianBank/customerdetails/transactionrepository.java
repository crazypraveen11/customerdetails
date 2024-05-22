package IndianBank.customerdetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface transactionrepository extends JpaRepository<transactionEntity,Long>
{
    public List<transactionEntity> findAllByAccount(accountentity data);
}
