package IndianBank.customerdetails;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class transactionservice 
{
    @Autowired
    transactionrepository trepo;

    public transactionEntity createtransaction(transactionEntity transactiondetails)
    {
        return trepo.save(transactiondetails);
    }

    public List<transactionEntity> listtransaction()
    {
        return trepo.findAll();
    }

    public Optional<transactionEntity> findonetransaction(Long transactionnumber)
    {
        return trepo.findById(transactionnumber);
    }

    public void deleteTransaction(Long id)
    {
        trepo.deleteById(id);
    }
    
    public List<transactionEntity> gettransactionfromoneuser(accountentity data)
    {
        return trepo.findAllByAccount(data);
    }
}
